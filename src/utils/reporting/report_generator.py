import json
import os
import html as html_module
from typing import Dict, Optional
from utils.tracking.logger import setup_logger
from core.evaluation.evaluation import estrai_metodo_singolo

logger = setup_logger()


def genera_report_html(
    metrics_file_path: str,
    output_html_path: Optional[str] = None,
    include_charts: bool = True,
) -> str:
    # Generate a complete and dynamic HTML report from JSON metrics for experiments regarding a tested method
    if not os.path.exists(metrics_file_path):
        logger.error(f"Metrics file not found: {metrics_file_path}")
        return ""
    
    with open(metrics_file_path, "r", encoding="utf-8") as f:
        metrics_data = json.load(f)
    
    if not output_html_path:
        base_dir = os.path.dirname(metrics_file_path)
        base_name = os.path.basename(metrics_file_path).replace(".json", "")
        output_html_path = os.path.join(base_dir, f"{base_name}_report.html")
    
    html_content = genera_html_content(metrics_data, include_charts)
    
    with open(output_html_path, "w", encoding="utf-8") as f:
        f.write(html_content)
    
    logger.info(f"HTML report generated: {output_html_path}")
    return output_html_path


def genera_html_content(metrics_data: Dict, include_charts: bool = True) -> str:
    """Generate the complete HTML content"""
    
    esperimenti = metrics_data.get("experiments", {})
    info_generale = metrics_data.get("general_info", {})
    
    # Prepare data for charts - uses only the first experiment (no longer multiple versions)
    versioni = list(esperimenti.keys())
    
    # Calculate summary statistics BASED ON REFINEMENTS in retry_history
    # instead of "versions" (which no longer exist)
    if versioni:
        primo_esperimento = esperimenti[versioni[0]]
        retry_history = primo_esperimento.get("retry_history", [])
        
        # Extract all entries of type "refinement" and "initial_method_generation"
        refinement_entries = [
            r
            for r in retry_history
            if r.get("type") in ["refinement", "initial_method_generation"]
        ]
        
        if refinement_entries:
            similarities = [
                r.get("overall_similarity", 0)
                for r in refinement_entries
                if r.get("overall_similarity") is not None
            ]
            avg_similarity = (
                sum(similarities) / len(similarities) if similarities else 0
            )
            max_similarity = max(similarities) if similarities else 0
            min_similarity = min(similarities) if similarities else 0
            num_refinements = len(
                [r for r in refinement_entries if r.get("type") == "refinement"]
            )
        else:
            # Fallback: use final values if there are no refinements
            avg_similarity = primo_esperimento["metrics"].get("overall_similarity", 0) or 0
            max_similarity = avg_similarity
            min_similarity = avg_similarity
            num_refinements = 0
    else:
        avg_similarity = 0
        max_similarity = 0
        min_similarity = 0
        num_refinements = 0
    
    # Extract original code metrics
    metriche_originale = {}
    if versioni:
        primo_esperimento = esperimenti[versioni[0]]
        metriche_originale = primo_esperimento.get("original_metrics", {})
    
    loc = metriche_originale.get("loc", "N/A")
    complexity = metriche_originale.get("complexity", "N/A")

    # Data for charts (no longer used but kept for backward compatibility)
    similarita_data = [avg_similarity]
    ast_data = (
        [primo_esperimento["metrics"].get("ast_similarity", 0)] if versioni else [0]
    )
    crystalbleu_data = (
        [primo_esperimento["metrics"].get("crystalbleu_similarity", 0)]
        if versioni
        else [0]
    )
    embedding_data = (
        [primo_esperimento["metrics"].get("embedding_similarity", 0)]
        if versioni
        else [0]
    )
    passed_count = sum(
        1 for v in versioni if esperimenti[v].get("passes_evaluation", False)
    )
    
    html = f"""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Experiment Report - {info_generale.get("tested_class", "N/A")}</title>
    <style>
        * {{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }}
        body {{
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 20px;
            min-height: 100vh;
        }}
        .container {{
            max-width: 1400px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            overflow: hidden;
        }}
        .header {{
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }}
        .header h1 {{
            font-size: 2.5em;
            margin-bottom: 10px;
        }}
        .header p {{
            font-size: 1.1em;
            opacity: 0.9;
        }}
        .content {{
            padding: 30px;
        }}
        .info-section {{
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }}
        .info-grid {{
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 15px;
            margin-top: 15px;
        }}
        .info-item {{
            background: white;
            padding: 15px;
            border-radius: 5px;
            border-left: 4px solid #667eea;
        }}
        .info-item label {{
            font-weight: bold;
            color: #666;
            display: block;
            margin-bottom: 5px;
        }}
        .info-item value {{
            font-size: 1.2em;
            color: #333;
        }}
        .stats-section {{
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 25px;
            border-radius: 8px;
            margin-bottom: 30px;
        }}
        .stats-grid {{
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 15px;
        }}
        .stat-card {{
            background: rgba(255, 255, 255, 0.1);
            padding: 20px;
            border-radius: 8px;
            text-align: center;
        }}
        .stat-label {{
            font-size: 0.9em;
            opacity: 0.9;
            margin-bottom: 10px;
        }}
        .stat-value {{
            font-size: 2.5em;
            font-weight: bold;
        }}
        .comparison-table {{
            background: white;
            border-radius: 8px;
            overflow: hidden;
            margin-bottom: 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }}
        table {{
            width: 100%;
            border-collapse: collapse;
        }}
        th, td {{
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }}
        th {{
            background: #667eea;
            color: white;
            font-weight: bold;
            position: sticky;
            top: 0;
        }}
        tr:hover {{
            background: #f8f9fa;
        }}
        .code-section {{
            margin-top: 30px;
            margin-bottom: 30px;
        }}
        .code-container {{
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-top: 15px;
        }}
        @media (max-width: 768px) {{
            .code-container {{
                grid-template-columns: 1fr;
            }}
        }}
        .code-container:has(.code-box:only-child) {{
            grid-template-columns: 1fr;
        }}
        .code-box {{
            background: #f8f9fa;
            border-radius: 8px;
            padding: 15px;
            border-left: 4px solid #667eea;
            overflow: visible;
        }}
        .code-box h3 {{
            margin-bottom: 10px;
            color: #333;
        }}
        .code-box.original {{
            border-left-color: #28a745;
        }}
        .code-box.regenerated {{
            border-left-color: #dc3545;
        }}
        pre {{
            background: #2d2d2d;
            color: #f8f8f2;
            padding: 15px;
            border-radius: 5px;
            overflow-x: auto;
            font-family: 'Consolas', 'Monaco', monospace;
            font-size: 0.9em;
            line-height: 1.5;
            margin-top: 10px;
            white-space: pre-wrap;
            word-wrap: break-word;
            max-height: none;
        }}
        .prompt-section {{
            background: #fff3cd;
            border-left: 4px solid #ffc107;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }}
        .prompt-section h3 {{
            margin-bottom: 15px;
            color: #856404;
        }}
        .prompt-box {{
            background: white;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 15px;
        }}
        .prompt-label {{
            font-weight: bold;
            color: #856404;
            margin-bottom: 10px;
        }}
        .prompt-content {{
            color: #333;
            white-space: pre-wrap;
            font-family: 'Consolas', 'Monaco', monospace;
            font-size: 0.9em;
        }}
        .metrics-section {{
            margin-top: 30px;
        }}
        .version-card {{
            background: #f8f9fa;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            border-left: 5px solid #667eea;
        }}
        .version-header {{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }}
        .version-title {{
            font-size: 1.5em;
            font-weight: bold;
            color: #333;
        }}
        .pass-badge {{
            padding: 5px 15px;
            border-radius: 20px;
            font-weight: bold;
            font-size: 0.9em;
        }}
        .pass-badge.success {{
            background: #28a745;
            color: white;
        }}
        .pass-badge.fail {{
            background: #dc3545;
            color: white;
        }}
        .metrics-grid {{
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-top: 15px;
        }}
        .metric-card {{
            background: white;
            padding: 15px;
            border-radius: 5px;
            text-align: center;
        }}
        .metric-label {{
            font-size: 0.9em;
            color: #666;
            margin-bottom: 5px;
        }}
        .metric-value {{
            font-size: 2em;
            font-weight: bold;
            color: #667eea;
        }}
        .chart-container {{
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
            min-height: 500px;
            height: auto;
        }}
        .chart-container canvas {{
            max-height: 500px !important;
        }}
        .progress-bar {{
            width: 100%;
            height: 30px;
            background: #e9ecef;
            border-radius: 15px;
            overflow: hidden;
            margin-top: 10px;
        }}
        .progress-fill {{
            height: 100%;
            background: linear-gradient(90deg, #667eea 0%, #764ba2 100%);
            transition: width 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
        }}
        .timestamp {{
            color: #666;
            font-size: 0.9em;
            margin-top: 10px;
        }}
        h2 {{
            color: #333;
            margin-bottom: 15px;
        }}
        h3 {{
            color: #555;
            margin-bottom: 10px;
        }}
        .toggle-btn {{
            background: #667eea;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
            font-size: 0.9em;
        }}
        .toggle-btn:hover {{
            background: #5568d3;
        }}
        .collapsible {{
            display: none;
        }}
        .collapsible.show {{
            display: block;
        }}
        .full-prompt-modal {{
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.7);
            overflow: auto;
        }}
        .full-prompt-modal.show {{
            display: block;
        }}
        .full-prompt-content {{
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 90%;
            max-width: 1200px;
            border-radius: 10px;
            max-height: 80vh;
            overflow-y: auto;
        }}
        .full-prompt-header {{
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 2px solid #667eea;
        }}
        .close-btn {{
            background: #dc3545;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.1em;
        }}
        .close-btn:hover {{
            background: #c82333;
        }}
        .selezione-section {{
            background: #e7f3ff;
            border-left: 4px solid #2196F3;
            padding: 15px;
            border-radius: 8px;
            margin-top: 15px;
        }}
        .selezione-section h4 {{
            margin-bottom: 10px;
            color: #1976D2;
        }}
        .selezione-classes {{
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
            margin-top: 10px;
        }}
        .selezione-class-badge {{
            background: white;
            padding: 5px 12px;
            border-radius: 15px;
            border: 1px solid #2196F3;
            font-size: 0.9em;
            color: #1976D2;
        }}
        .test-expandable {{
            max-height: 300px;
            overflow: hidden;
            transition: max-height 0.3s ease;
        }}
        .test-expandable.expanded {{
            max-height: none;
        }}
        .expand-test-btn {{
            background: #667eea;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
            font-size: 0.9em;
        }}
        .expand-test-btn:hover {{
            background: #5568d3;
        }}
        .view-prompt-btn {{
            background: #ffc107;
            color: #333;
            border: none;
            padding: 6px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 0.85em;
            margin-left: 10px;
        }}
        .view-prompt-btn:hover {{
            background: #e0a800;
        }}
    </style>
"""
    
    if include_charts:
        html += """
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
"""
    
    html += """
</head>
<body>
    <div class="container">
        <div class="header">
            <h1> Experiment Report</h1>
            <p>Detailed analysis of similarity metrics</p>
        </div>
        <div class="content">
            <div class="info-section">
                <h2>General Information</h2>
                <div class="info-grid">
                    <div class="info-item">
                        <label>Tested Class</label>
                        <value>{classe_testata}</value>
                    </div>
                    <div class="info-item">
                        <label>Tested Method</label>
                        <value>{metodo_testato}</value>
                    </div>
                    <div class="info-item">
                        <label>Refinement Iterations</label>
                        <value>{num_refinements}</value>
                    </div>
                    <div class="info-item">
                        <label>Last Updated</label>
                        <value>{ultimo_aggiornamento}</value>
                    </div>
                    {descrizione_item}
                    {soglia_item}
                    {soglia_coverage_item}
                    {provider_item}
                    {modello_item}
                    {complexity_item}
                    {loc_item}
                </div>
            </div>
""".format(
        classe_testata=info_generale.get("tested_class", "N/A"),
        metodo_testato=info_generale.get("tested_method", "N/A"),
        num_refinements=num_refinements,
        ultimo_aggiornamento=info_generale.get("last_update", "N/A"),
        descrizione_item=f'<div class="info-item"><label>Description</label><value>{html_module.escape(info_generale.get("description", "N/A"))}</value></div>'
        if info_generale.get("description")
        else "",
        soglia_item=f'<div class="info-item"><label>Similarity Threshold ({"Dynamic" if info_generale.get("usa_soglia_dinamica", True) else "Fixed"})</label><value>{info_generale.get("similarity_threshold", 0):.2%}</value></div>'
        if info_generale.get("similarity_threshold") is not None
        else "",
        soglia_coverage_item=f'<div class="info-item"><label>Line Coverage Threshold</label><value>{info_generale.get("soglia_coverage", 0):.1f}%</value></div>'
        if info_generale.get("soglia_coverage") is not None
        and info_generale.get("soglia_coverage", 0) > 0
        else "",
        provider_item=f'<div class="info-item"><label>Provider</label><value>{html_module.escape(info_generale.get("provider", "N/A"))}</value></div>'
        if info_generale.get("provider")
        else "",
        modello_item=f'<div class="info-item"><label>Model</label><value>{html_module.escape(info_generale.get("model", "N/A"))}</value></div>'
        if info_generale.get("model")
        else "",
        complexity_item=f'<div class="info-item"><label>Cyclomatic Complexity</label><value>{complexity}</value></div>' if complexity != "N/A" else "",
        loc_item=f'<div class="info-item"><label>Lines of Code (LOC)</label><value>{loc}</value></div>' if loc != "N/A" else "",
    )
    
    # Summary statistics
    html += f"""
            <div class="stats-section">
                <h2>Summary Statistics</h2>
                <div class="stats-grid">
                    <div class="stat-card">
                        <div class="stat-label">Average Similarity</div>
                        <div class="stat-value">{avg_similarity:.1%}</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-label">Maximum Similarity</div>
                        <div class="stat-value">{max_similarity:.1%}</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-label">Minimum Similarity</div>
                        <div class="stat-value">{min_similarity:.1%}</div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-label">Refinement Iterations</div>
                        <div class="stat-value">{num_refinements}</div>
                    </div>
                </div>
            </div>
"""
    
    # Comparative version table
    if len(versioni) > 1:
        html += """
            <div class="comparison-table">
                <h2>📋 Version Comparison Table</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Version</th>
                            <th>Overall Similarity</th>
                            <th>Embedding</th>
                            <th>CrystalBLEU</th>
                            <th>String</th>
                            <th>Token</th>
                            <th>AST</th>
                            <th>Pass</th>
                            <th>Timestamp</th>
                        </tr>
                    </thead>
                    <tbody>
"""
        for versione in versioni:
            esp = esperimenti[versione]
            met = esp.get("metrics", {})
            weights = met.get("weights", {})
            html += f"""
                        <tr>
                            <td><strong>{versione.replace("_", " ").title()}</strong></td>
                            <td>{met.get("overall_similarity", 0):.2%}</td>
                            <td>{met.get("embedding_similarity", 0):.3f}</td>
                            <td>{met.get("crystalbleu_similarity", 0):.3f}</td>
                            <td>{met.get("string_similarity", 0):.3f}</td>
                            <td>{met.get("token_similarity", 0):.3f}</td>
                            <td>{met.get("ast_similarity", 0):.3f}</td>
                            <td>{"✓" if esp.get("passes_evaluation", False) else "✗"}</td>
                            <td>{esp.get("timestamp", "N/A")[:19]}</td>
                        </tr>
"""
        html += """
                    </tbody>
                </table>
            </div>
"""
    
    # Metrics section per version with code and prompt
    html += """
            <div class="metrics-section">
                <h2>Experiment Results</h2>
"""
    
    for versione in versioni:
        esperimento = esperimenti[versione]
        metriche = esperimento.get("metrics", {})
        passa_valutazione = esperimento.get("passes_evaluation", False)
        overall = metriche.get("overall_similarity", 0) or 0  # Converts None to 0
        codice = esperimento.get("codice", {})
        prompt = esperimento.get("prompt", {})
        fasi_eseguite = esperimento.get("executed_phases", {})
        contesto = esperimento.get("context", {})
        selezione_info = contesto.get("dependency_selection", {})
        test_prima_fase = contesto.get("test_prima_fase", "")
        prima_fase = fasi_eseguite.get("first_phase", False)
        seconda_fase = fasi_eseguite.get("second_phase", False)
        
        # First try to use methods saved in the JSON (with JavaDoc for the original)
        metodo_originale = esperimento.get("original_method_display", "")
        metodo_rigenerato = esperimento.get("regenerated_method_display", "")
        
        # If not in the JSON, fall back to reading from file
        percorsi = esperimento.get("paths", {})
        file_originale = percorsi.get("original_file", "")
        file_rigenerato = percorsi.get("regenerated_file", "")
        nome_metodo = esperimento.get("tested_method", "")
        
        # Read original method from file if not saved in the JSON
        if not metodo_originale and file_originale and os.path.exists(file_originale) and nome_metodo:
            try:
                with open(file_originale, "r", encoding="utf-8") as f:
                    classe_originale = f.read()
                # Try first with JavaDoc
                from core.evaluation.evaluation import estrai_metodo_con_javadoc
                metodo_originale = estrai_metodo_con_javadoc(classe_originale, nome_metodo) or ""
                # If it fails, try without
                if not metodo_originale:
                    metodo_originale = estrai_metodo_singolo(classe_originale, nome_metodo) or ""
            except Exception as e:
                logger.warning(
                    f"Error reading original method from {file_originale}: {e}"
                )
        
        # Read regenerated method from file if not saved in the JSON
        # NOTE: the regenerated file is now the original class (restored), so use retry_history
        if not metodo_rigenerato:
            # Try to extract from retry_history
            retry_history = esperimento.get("retry_history", [])
            for entry in reversed(retry_history):
                if entry.get("regenerated_method"):
                    metodo_rigenerato = entry.get("regenerated_method")
                    break
            # If still empty, try from file (but it will be the original since it was restored)
            if not metodo_rigenerato and file_rigenerato and os.path.exists(file_rigenerato) and nome_metodo:
                try:
                    with open(file_rigenerato, "r", encoding="utf-8") as f:
                        classe_rigenerata = f.read()
                    metodo_rigenerato = (
                        estrai_metodo_singolo(classe_rigenerata, nome_metodo) or ""
                    )
                except Exception as e:
                    logger.warning(
                        f"Error reading regenerated method from {file_rigenerato}: {e}"
                    )
        # Use the complete prompt if available, otherwise the YAML one
        prompt_test = prompt.get("generazione_test_completo") or prompt.get(
            "generazione_test", ""
        )
        prompt_rigenera = prompt.get("rigenerazione_metodo_completo") or prompt.get(
            "rigenerazione_metodo", ""
        )
        
        # Determine which phases were executed
        # Extract coverage threshold to display even when not proceeding to the second phase
        soglia_coverage_info = info_generale.get("soglia_coverage", None)
        soglia_info_text = ""
        if soglia_coverage_info is not None and soglia_coverage_info > 0:
            soglia_info_text = f" (Coverage Threshold: {soglia_coverage_info:.1f}%)"
        
        if prima_fase and seconda_fase:
            fasi_descrizione = "✓ Both phases executed"
        elif prima_fase:
            fasi_descrizione = f"✓ First phase only (test generation){soglia_info_text}"
        elif seconda_fase:
            fasi_descrizione = "✓ Second phase only (method regeneration)"
        else:
            fasi_descrizione = f"No phase executed{soglia_info_text}"
        
        html += f"""
                <div class="version-card">
                    <div class="version-header">
                        <div>
                            <div class="version-title">Experiment</div>
                            <div style="font-size: 0.9em; color: #666; margin-top: 5px; font-weight: normal;">{fasi_descrizione}</div>
                        </div>
                        <div class="pass-badge {"success" if passa_valutazione else "fail"}">
                            {"✓ Pass" if passa_valutazione else "✗ Fail"}
                        </div>
                    </div>
                    <div class="metric-card">
                        <div class="metric-label">Overall Similarity</div>
                        <div class="metric-value">{overall:.2%}</div>
                        <div class="progress-bar">
                            <div class="progress-fill" style="width: {overall * 100}%">{overall:.1%}</div>
                        </div>
                    </div>
                    <div class="metrics-grid">
                        <div class="metric-card">
                            <div class="metric-label">UniXcoder Similarity</div>
                            <div class="metric-value">{metriche.get("embedding_similarity", 0):.3f}</div>
                        </div>
                        <div class="metric-card">
                            <div class="metric-label">CrystalBLEU</div>
                            <div class="metric-value">{metriche.get("crystalbleu_similarity", 0):.3f}</div>
                        </div>
                        <div class="metric-card">
                            <div class="metric-label">String Similarity</div>
                            <div class="metric-value">{metriche.get("string_similarity", 0):.3f}</div>
                        </div>
                        <div class="metric-card">
                            <div class="metric-label">Token Similarity</div>
                            <div class="metric-value">{metriche.get("token_similarity", 0):.3f}</div>
                        </div>
                        <div class="metric-card">
                            <div class="metric-label">AST Similarity</div>
                            <div class="metric-value">{metriche.get("ast_similarity", 0):.3f}</div>
                        </div>
                    </div>
                    <div class="metrics-grid" style="margin-top:10px;">
                        <div class="metric-card">
                            <div class="metric-label">Metric Weights</div>
                            <div style="font-size:0.9em;color:#444;">
                                Embedding: {metrica_peso(metriche, "embedding_similarity")}&nbsp;|&nbsp;
                                CrystalBLEU: {metrica_peso(metriche, "crystalbleu_similarity")}&nbsp;|&nbsp;
                                String: {metrica_peso(metriche, "string_similarity")}&nbsp;|&nbsp;
                                Token: {metrica_peso(metriche, "token_similarity")}&nbsp;|&nbsp;
                                AST: {metrica_peso(metriche, "ast_similarity")}
                            </div>
                        </div>
                    </div>
"""
        
        # Test results
        test_results = esperimento.get("test_results", {})
        if test_results:
            # Show final method sources first
            if metodo_originale or metodo_rigenerato:
                html += f"""
                    <div class="code-section" style="margin-top: 30px; margin-bottom: 30px;">
                        <h2 style="color: #333; border-bottom: 3px solid #667eea; padding-bottom: 10px; margin-bottom: 20px;">📄 Final Method Comparison</h2>
                        <div class="code-container">
"""
                if metodo_originale:
                    html += f"""
                            <div class="code-box original">
                                <h3 style="margin-top: 0; color: #28a745;">✓ Original Method</h3>
                                <pre><code>{html_module.escape(metodo_originale)}</code></pre>
                            </div>
"""
                if metodo_rigenerato:
                    html += f"""
                            <div class="code-box regenerated">
                                <h3 style="margin-top: 0; color: #dc3545;">🔄 Regenerated Method (Final)</h3>
                                <pre><code>{html_module.escape(metodo_rigenerato)}</code></pre>
                            </div>
"""
                html += """
                        </div>
                    </div>
"""
            
            html += """
                    <div class="code-section" style="margin-top: 20px;">
                        <h2 style="color: #333; border-bottom: 3px solid #667eea; padding-bottom: 10px; margin-bottom: 20px;"> Final Test Results</h2>
"""
            # Extract coverage threshold if available (once for all sections)
            soglia_coverage = info_generale.get("soglia_coverage", None)
            soglia_display = (
                f" (Target: {soglia_coverage:.1f}%)"
                if soglia_coverage is not None and soglia_coverage > 0
                else ""
            )
            
            # Tests on original method
            if "original" in test_results:
                test_orig = test_results["original"]
                success_orig = test_orig.get("success", False)
                tests_passed_orig = test_orig.get("tests_passed", 0)
                tests_total_orig = test_orig.get("tests_total", 0)
                line_cov_orig = test_orig.get("line_coverage", 0.0)
                branch_cov_orig = test_orig.get("branch_coverage", 0.0)
                has_comp_errors_orig = test_orig.get("has_compilation_errors", False)
                
                # Extract passed, failed (assert) and runtime error tests
                test_info_orig = test_orig.get("test_info", {})
                test_validi_orig = test_info_orig.get("valid_tests", [])
                test_non_validi_orig = test_info_orig.get("invalid_tests", [])
                
                # Try to extract failed and runtime error tests separately
                failed_test_names_only = test_orig.get("failed_test_names_only", [])
                error_test_names = test_orig.get("error_test_names", [])
                
                # If not available, try to deduce from test_info or test_non_validi
                if not failed_test_names_only and not error_test_names:
                    # Try from test_info
                    failed_test_names_only = test_info_orig.get(
                        "failed_assert_tests", []
                    )
                    error_test_names = test_info_orig.get("runtime_error_tests", [])
                
                # If still not available, try to deduce from test_non_validi
                if (
                    not failed_test_names_only
                    and not error_test_names
                    and test_non_validi_orig
                ):
                    # Cannot distinguish, so put everything in failed
                    failed_test_names_only = test_non_validi_orig
                
                # Calculate tests_total as sum of passed and failed for consistency
                tests_total_orig_calc = (
                    tests_passed_orig
                    + len(failed_test_names_only)
                    + len(error_test_names)
                )
                if tests_total_orig_calc > 0:
                    tests_total_orig = tests_total_orig_calc
                
                html += f"""
                        <div class="code-box" style="margin-bottom: 15px;">
                            <h3>Tests on Original Method</h3>
                            <div style="margin: 10px 0;">
                                <strong>Status:</strong> 
                                <span class="pass-badge {"success" if success_orig else "fail"}" style="margin-left: 10px;">
                                    {"✓ Passed" if success_orig else "✗ Failed"}
                                </span>
                                {f'<span style="margin-left: 10px; padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24;">WARNING: Compilation Errors</span>' if has_comp_errors_orig else ""}
                            </div>
                            <div style="margin: 10px 0;">
                                <strong>Test Results:</strong>
                                <div style="margin-top: 5px;">
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #d4edda; color: #155724; margin-right: 10px;">
                                        ✓ Passed: {tests_passed_orig}
                                    </span>
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24; margin-right: 10px;">
                                        ✗ Failed: {len(failed_test_names_only) + len(error_test_names)}
                                    </span>
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #fff3cd; color: #856404;">
                                         Total: {tests_total_orig}
                                    </span>
                                </div>
                            </div>
                            <div style="margin: 10px 0;">
                                <strong>Test:</strong> {tests_passed_orig}/{tests_total_orig}
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {(tests_passed_orig / tests_total_orig * 100) if tests_total_orig > 0 else 0}%; font-size: 0.8em;">
                                        {tests_passed_orig}/{tests_total_orig}
                                    </div>
                                </div>
                            </div>
"""
                if test_validi_orig:
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #d4edda; border-radius: 5px; border-left: 4px solid #28a745;">
                                <strong style="color: #155724;">✓ Passed Tests ({len(test_validi_orig)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in test_validi_orig:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #155724;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                if failed_test_names_only:
                    # Filtra "UnknownTest" dalla lista
                    failed_test_names_only_filtered = [
                        t for t in failed_test_names_only if t != "UnknownTest"
                    ]
                    if failed_test_names_only_filtered:
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #fff3cd; border-radius: 5px; border-left: 4px solid #ffc107;">
                                <strong style="color: #856404;"> Tests with Failed Assertions ({len(failed_test_names_only_filtered)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in failed_test_names_only_filtered:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #856404;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                if error_test_names:
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">💥 Tests with Runtime Errors ({len(error_test_names)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in error_test_names:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                # Fallback: if we couldn't distinguish, show test_non_validi
                if (
                    not failed_test_names_only
                    and not error_test_names
                    and test_non_validi_orig
                ):
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">✗ Failed Tests ({len(test_non_validi_orig)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in test_non_validi_orig:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                # Extract coverage threshold if available
                soglia_coverage = info_generale.get("soglia_coverage", None)
                soglia_display = (
                    f" (Target: {soglia_coverage:.1f}%)"
                    if soglia_coverage is not None and soglia_coverage > 0
                    else ""
                )
                
                html += f"""
                            <div style="margin: 10px 0;">
                                <strong>Line Coverage:</strong> {line_cov_orig:.2f}%{soglia_display}
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {line_cov_orig}%; background: {"#28a745" if (soglia_coverage and line_cov_orig >= soglia_coverage) or (not soglia_coverage and line_cov_orig >= 80) else "#ffc107" if line_cov_orig >= 50 else "#dc3545"}; font-size: 0.8em;">
                                        {line_cov_orig:.1f}%
                                    </div>
                                </div>
                            </div>
                            <div style="margin: 10px 0;">
                                <strong>Branch Coverage:</strong> {branch_cov_orig:.2f}% {"(N/A - method without branches)" if branch_cov_orig == 0.0 and line_cov_orig > 0 else ""}
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {branch_cov_orig}%; background: {"#28a745" if branch_cov_orig >= 80 else "#ffc107" if branch_cov_orig >= 50 else "#dc3545"}; font-size: 0.8em;">
                                        {branch_cov_orig:.1f}%
                                    </div>
                                </div>
                            </div>
"""
                html += """
                        </div>
"""
            
            # Tests on regenerated method
            if "regenerated" in test_results:
                test_regen = test_results["regenerated"]
                success_regen = test_regen.get("success", False)
                tests_passed_regen = test_regen.get("tests_passed", 0)
                tests_total_regen = test_regen.get("tests_total", 0)
                line_cov_regen = test_regen.get("line_coverage", 0.0)
                branch_cov_regen = test_regen.get("branch_coverage", 0.0)
                has_comp_errors_regen = test_regen.get("has_compilation_errors", False)
                
                # Extract passed, failed (assert) and runtime error tests
                test_info_regen = test_regen.get("test_info", {})
                test_validi_regen = test_info_regen.get("valid_tests", [])
                test_non_validi_regen = test_info_regen.get("invalid_tests", [])
                
                # Try to extract failed and runtime error tests separately
                failed_test_names_only_regen = test_regen.get(
                    "failed_test_names_only", []
                )
                error_test_names_regen = test_regen.get("error_test_names", [])
                
                # If not available, try to deduce from test_info or test_non_validi
                if not failed_test_names_only_regen and not error_test_names_regen:
                    # Try from test_info
                    failed_test_names_only_regen = test_info_regen.get(
                        "failed_assert_tests", []
                    )
                    error_test_names_regen = test_info_regen.get(
                        "runtime_error_tests", []
                    )
                
                # If still not available, try to deduce from test_non_validi
                if (
                    not failed_test_names_only_regen
                    and not error_test_names_regen
                    and test_non_validi_regen
                ):
                    # Cannot distinguish, so put everything in failed
                    failed_test_names_only_regen = test_non_validi_regen
                
                # Calculate tests_total as sum of passed and failed for consistency
                tests_total_regen_calc = (
                    tests_passed_regen
                    + len(failed_test_names_only_regen)
                    + len(error_test_names_regen)
                )
                if tests_total_regen_calc > 0:
                    tests_total_regen = tests_total_regen_calc
                
                html += f"""
                        <div class="code-box" style="margin-bottom: 15px;">
                            <h3>Tests on Regenerated Method</h3>
                            <div style="margin: 10px 0;">
                                <strong>Status:</strong> 
                                <span class="pass-badge {"success" if success_regen else "fail"}" style="margin-left: 10px;">
                                    {"✓ Passed" if success_regen else "✗ Failed"}
                                </span>
                                {f'<span style="margin-left: 10px; padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24;">WARNING: Compilation Errors</span>' if has_comp_errors_regen else ""}
                            </div>
                            <div style="margin: 10px 0;">
                                <strong>Test Results:</strong>
                                <div style="margin-top: 5px;">
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #d4edda; color: #155724; margin-right: 10px;">
                                        ✓ Passed: {tests_passed_regen}
                                    </span>
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24; margin-right: 10px;">
                                        ✗ Failed: {len(failed_test_names_only_regen) + len(error_test_names_regen)}
                                    </span>
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #fff3cd; color: #856404;">
                                         Total: {tests_total_regen}
                                    </span>
                                </div>
                            </div>
                            <div style="margin: 10px 0;">
                                <strong>Test:</strong> {tests_passed_regen}/{tests_total_regen}
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {(tests_passed_regen / tests_total_regen * 100) if tests_total_regen > 0 else 0}%; font-size: 0.8em;">
                                        {tests_passed_regen}/{tests_total_regen}
                                    </div>
                                </div>
                            </div>
"""
                if test_validi_regen:
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #d4edda; border-radius: 5px; border-left: 4px solid #28a745;">
                                <strong style="color: #155724;">✓ Passed Tests ({len(test_validi_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in test_validi_regen:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #155724;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                if failed_test_names_only_regen:
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #fff3cd; border-radius: 5px; border-left: 4px solid #ffc107;">
                                <strong style="color: #856404;"> Tests with Failed Assertions ({len(failed_test_names_only_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in failed_test_names_only_regen:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #856404;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                if error_test_names_regen:
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">💥 Tests with Runtime Errors ({len(error_test_names_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in error_test_names_regen:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                # Fallback: if we couldn't distinguish, show test_non_validi
                if (
                    not failed_test_names_only_regen
                    and not error_test_names_regen
                    and test_non_validi_regen
                ):
                    html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">✗ Failed Tests ({len(test_non_validi_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in test_non_validi_regen:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                html += f"""
                            <div style="margin: 10px 0;">
                                <strong>Line Coverage:</strong> {line_cov_regen:.2f}%
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {line_cov_regen}%; background: {"#28a745" if line_cov_regen >= 80 else "#ffc107" if line_cov_regen >= 50 else "#dc3545"}; font-size: 0.8em;">
                                        {line_cov_regen:.1f}%
                                    </div>
                                </div>
                            </div>
                            <div style="margin: 10px 0;">
                                <strong>Branch Coverage:</strong> {branch_cov_regen:.2f}% {"(N/A - method without branches)" if branch_cov_regen == 0.0 and line_cov_regen > 0 else ""}
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {branch_cov_regen}%; background: {"#28a745" if branch_cov_regen >= 80 else "#ffc107" if branch_cov_regen >= 50 else "#dc3545"}; font-size: 0.8em;">
                                        {branch_cov_regen:.1f}%
                                    </div>
                                </div>
                            </div>
"""
                html += """
                        </div>
"""
            
            html += """
                    </div>
"""
        
        # Retry/Refinement History section (only if there are significant entries)
        retry_history = esperimento.get("retry_history", [])
        # Check if there are significant entries (repair, refinement, initial_method_generation)
        # Exclude initial_test_execution and initial_regenerated_test_execution from display
        has_significant_entries = any(
            retry.get("type") in ["repair", "refinement", "initial_method_generation", "repair_after_refinement"] 
            for retry in retry_history
        )
        
        if retry_history and has_significant_entries:
            # Check if there is an initial_method_generation in the history
            has_initial_method_generation = any(
                r.get("type") == "initial_method_generation"
                for r in retry_history
            )
            
            # Extract failed test info from initial_test_execution and initial_regenerated_test_execution entries
            # to use as fallback when initial_method_generation doesn't have test_info
            initial_test_execution_info = {}
            initial_regenerated_test_execution_info = {}
            
            if has_initial_method_generation:
                for r in retry_history:
                    if r.get("type") == "initial_test_execution":
                        test_res_orig = r.get("original_test_results", {})
                        test_info_orig = test_res_orig.get("test_info", {})
                        initial_test_execution_info = {
                            "failed_assert_tests": test_info_orig.get("failed_assert_tests", []),
                            "runtime_error_tests": test_info_orig.get("runtime_error_tests", []),
                            "invalid_tests": test_info_orig.get("invalid_tests", []),
                            "valid_tests": test_info_orig.get("valid_tests", [])
                        }
                    elif r.get("type") == "initial_regenerated_test_execution":
                        test_res_regen = r.get("regenerated_test_results", {})
                        test_info_regen = test_res_regen.get("test_info", {})
                        initial_regenerated_test_execution_info = {
                            "failed_assert_tests": test_info_regen.get("failed_assert_tests", []),
                            "runtime_error_tests": test_info_regen.get("runtime_error_tests", []),
                            "invalid_tests": test_info_regen.get("invalid_tests", []),
                            "valid_tests": test_info_regen.get("valid_tests", [])
                        }
            
            html += """
                    <div class="code-section" style="margin-top: 20px;">
                        <h3>🔄 Retry/Refinement History</h3>
"""
            for idx, retry in enumerate(retry_history):
                retry_type = retry.get("type", "unknown")
                
                # If there is an initial_method_generation, skip initial_test_execution and initial_regenerated_test_execution
                # because initial_method_generation already contains the same information
                if retry_type in [
                    "initial_test_execution",
                    "initial_regenerated_test_execution",
                ]:
                    # Skip if there is also an initial_method_generation (avoid duplication)
                    if has_initial_method_generation:
                        continue
                
                    # Otherwise, show only if there are failed tests
                    test_res_check = retry.get("original_test_results") or retry.get("regenerated_test_results", {})
                    tests_passed_check = test_res_check.get("tests_passed", 0)
                    tests_total_check = test_res_check.get("tests_total", 0)
                    tests_failed_check = tests_total_check - tests_passed_check
                    
                    # Skip only if all tests passed (nothing interesting to show)
                    if tests_failed_check == 0:
                        continue

                retry_num = (
                    retry.get("repair_iteration")
                    or retry.get("refinement_iteration")
                    or retry.get("retry_number", idx)
                )
                timestamp = retry.get("timestamp", "N/A")
                
                # Initialize all common variables to avoid UnboundLocalError
                has_comp_errors = False
                errori_comp = None
                refinement_prompt = None
                righe_non_coperte = []
                show_refinement_prompt = False
                overall_similarity = 0.0
                passes_threshold = False
                metriche_sim = {}
                soglia_display = ""
                soglia_coverage = info_generale.get("soglia_coverage", None)
                
                # Initialize test variables (used in both refinement and other types)

                tests_total = 0
                tests_passed = 0
                tests_failed = 0
                failed_names = []
                failed_only = []
                error_names = []
                line_cov = 0.0
                branch_cov = 0.0
                test_validi = []
                test_non_validi = []
                
                # Initialize variables for refinement (original and regenerated)
                tests_total_orig = 0
                tests_passed_orig = 0
                tests_failed_orig = 0
                failed_only_orig = []
                error_names_orig = []
                line_cov_orig = 0.0
                branch_cov_orig = 0.0
                test_validi_orig = []
                test_non_validi_orig = []
                
                tests_total_regen = 0
                tests_passed_regen = 0
                tests_failed_regen = 0
                failed_only_regen = []
                error_names_regen = []
                line_cov_regen = 0.0
                branch_cov_regen = 0.0
                test_validi_regen = []
                test_non_validi_regen = []
                
                # Handle different retry types
                if retry_type == "repair":
                    test_res = retry.get("test_results", {})
                    retry_label = f"Repair Iteration {retry_num}"
                elif retry_type == "refinement":
                    test_res_orig = retry.get("original_test_results", {})
                    test_res_regen = retry.get("regenerated_test_results", {})
                    overall_similarity = retry.get("overall_similarity", 0.0) or 0.0
                    passes_threshold = retry.get("passes_threshold", False)
                    # Determine if this is the last refinement
                    is_last_refinement = idx == len(retry_history) - 1
                    retry_label = f"Refinement Iteration {retry_num}" + (
                        " (final)" if is_last_refinement else ""
                    )
                elif retry_type == "initial_method_generation":
                    test_res_orig = retry.get("original_test_results", {})
                    test_res_regen = retry.get("regenerated_test_results", {})
                    overall_similarity = retry.get("overall_similarity", 0.0) or 0.0
                    passes_threshold = retry.get("passes_threshold", False)
                    retry_label = "Initial Method Generation"
                elif retry_type == "initial_test_execution":
                    test_res = retry.get("original_test_results", {})
                    retry_label = "Initial Test Execution (Original Method)"
                elif retry_type == "initial_regenerated_test_execution":
                    test_res = retry.get("regenerated_test_results", {})
                    retry_label = "Initial Test Execution (Regenerated Method)"
                elif retry_type == "repair_after_refinement":
                    # Repair after refinement - simplified display
                    test_res = retry.get("test_results", {})
                    repair_iter = retry.get("repair_iteration", "?")
                    after_ref_iter = retry.get("after_refinement_iteration", "?")
                    retry_label = f"Repair {repair_iter} (after Refinement {after_ref_iter})"
                    has_comp_errors = retry.get("has_compilation_errors", False)
                    errori_comp = retry.get("compilation_errors", "")
                else:
                    # Fallback for old structure
                    test_res = retry.get("test_results", {})
                    retry_label = retry.get("retry_label") or (
                        "Initial Execution (after test generation)"
                        if retry_num == 0
                        else f"Retry {retry_num}"
                    )
                
                # Extract common information
                if retry_type in ["refinement", "initial_method_generation"]:
                    # For refinement, show both original and regenerated
                    tests_total_orig = test_res_orig.get("tests_total", 0)
                    tests_passed_orig = test_res_orig.get("tests_passed", 0)
                    tests_failed_orig = test_res_orig.get("tests_failed", 0)
                    line_cov_orig = test_res_orig.get("line_coverage", 0.0)
                    branch_cov_orig = test_res_orig.get("branch_coverage", 0.0)
                    
                    # Extract test_info to get correct classification
                    test_info_orig = test_res_orig.get("test_info", {})
                    test_validi_orig = test_info_orig.get(
                        "valid_tests", []
                    ) or test_res_orig.get("valid_tests", [])
                    test_non_validi_orig = test_info_orig.get(
                        "invalid_tests", []
                    ) or test_res_orig.get("invalid_tests", [])
                    
                    # Extract failed (assert) and runtime error tests separately
                    failed_only_orig = test_res_orig.get("failed_test_names_only", [])
                    error_names_orig = test_res_orig.get("error_test_names", [])
                    
                    # If not available, try from test_info
                    if not failed_only_orig and not error_names_orig:
                        failed_only_orig = test_info_orig.get("failed_assert_tests", [])
                        error_names_orig = test_info_orig.get("runtime_error_tests", [])
                    
                    # If still not available, try to deduce from test_non_validi
                    if (
                        not failed_only_orig
                        and not error_names_orig
                        and test_non_validi_orig
                    ):
                        # Cannot distinguish, so put everything in failed
                        failed_only_orig = test_non_validi_orig
                    
                    # If we still don't have the names but know there are failed tests,
                    # try using them from initial_test_execution entries (for initial_method_generation)
                    tests_failed_calc_orig = tests_total_orig - tests_passed_orig
                    if (
                        not failed_only_orig
                        and not error_names_orig
                        and tests_failed_calc_orig > 0
                        and retry_type == "initial_method_generation"
                        and initial_test_execution_info
                    ):
                        # Use information from initial_test_execution
                        failed_only_orig = initial_test_execution_info.get("failed_assert_tests", [])
                        error_names_orig = initial_test_execution_info.get("runtime_error_tests", [])
                        # If still not available, try test_non_validi
                        if not failed_only_orig and not error_names_orig:
                            failed_only_orig = initial_test_execution_info.get("invalid_tests", [])
                    
                    # If we still don't have the names, use placeholder as last resort
                    if (
                        not failed_only_orig
                        and not error_names_orig
                        and tests_failed_calc_orig > 0
                    ):
                        # We don't have the names, but we know there are failed tests
                        # We'll use a placeholder to indicate failed tests without names
                        failed_only_orig = ["(failed tests without name)"] * tests_failed_calc_orig if tests_failed_calc_orig > 0 else []
                    
                    # Calculate tests_total as sum of passed and failed for consistency
                    tests_total_orig_calc = (
                        tests_passed_orig
                        + len(failed_only_orig)
                        + len(error_names_orig)
                    )
                    if tests_total_orig_calc > 0:
                        tests_total_orig = tests_total_orig_calc
                    
                    tests_total_regen = test_res_regen.get("tests_total", 0)
                    tests_passed_regen = test_res_regen.get("tests_passed", 0)
                    tests_failed_regen = test_res_regen.get("tests_failed", 0)
                    line_cov_regen = test_res_regen.get("line_coverage", 0.0)
                    branch_cov_regen = test_res_regen.get("branch_coverage", 0.0)
                    
                    # Extract test_info to get correct classification
                    test_info_regen = test_res_regen.get("test_info", {})
                    test_validi_regen = test_info_regen.get(
                        "valid_tests", []
                    ) or test_res_regen.get("valid_tests", [])
                    test_non_validi_regen = test_info_regen.get(
                        "invalid_tests", []
                    ) or test_res_regen.get("invalid_tests", [])
                    
                    # Extract failed (assert) and runtime error tests separately
                    failed_only_regen = test_res_regen.get("failed_test_names_only", [])
                    error_names_regen = test_res_regen.get("error_test_names", [])
                    
                    # If not available, try from test_info
                    if not failed_only_regen and not error_names_regen:
                        failed_only_regen = test_info_regen.get(
                            "failed_assert_tests", []
                        )
                        error_names_regen = test_info_regen.get(
                            "runtime_error_tests", []
                        )
                    
                    # If still not available, try to deduce from test_non_validi
                    if (
                        not failed_only_regen
                        and not error_names_regen
                        and test_non_validi_regen
                    ):
                        # Cannot distinguish, so put everything in failed
                        failed_only_regen = test_non_validi_regen
                    
                    # If we still don't have the names but know there are failed tests,
                    # try using them from initial_regenerated_test_execution entries (for initial_method_generation)
                    tests_failed_calc_regen = tests_total_regen - tests_passed_regen
                    if (
                        not failed_only_regen
                        and not error_names_regen
                        and tests_failed_calc_regen > 0
                        and retry_type == "initial_method_generation"
                        and initial_regenerated_test_execution_info
                    ):
                        # Use information from initial_regenerated_test_execution
                        failed_only_regen = initial_regenerated_test_execution_info.get("failed_assert_tests", [])
                        error_names_regen = initial_regenerated_test_execution_info.get("runtime_error_tests", [])
                        # If still not available, try test_non_validi
                        if not failed_only_regen and not error_names_regen:
                            failed_only_regen = initial_regenerated_test_execution_info.get("invalid_tests", [])
                    
                    # If we still don't have the names, use placeholder as last resort
                    if (
                        not failed_only_regen
                        and not error_names_regen
                        and tests_failed_calc_regen > 0
                    ):
                        # We don't have the names, but we know there are failed tests
                        # We'll use a placeholder to indicate failed tests without names
                        failed_only_regen = ["(failed tests without name)"] * tests_failed_calc_regen if tests_failed_calc_regen > 0 else []
                    
                    # Calculate tests_total as sum of passed and failed for consistency
                    tests_total_regen_calc = (
                        tests_passed_regen
                        + len(failed_only_regen)
                        + len(error_names_regen)
                    )
                    if tests_total_regen_calc > 0:
                        tests_total_regen = tests_total_regen_calc
                else:
                    tests_total = test_res.get("tests_total", 0)
                    tests_passed = test_res.get("tests_passed", 0)
                    tests_failed = test_res.get("tests_failed", 0)
                    failed_names = test_res.get("failed_test_names", [])
                    failed_only = test_res.get(
                        "failed_test_names_only", []
                    ) or test_res.get("failed_test_names", [])
                    error_names = test_res.get("error_test_names", [])
                    line_cov = test_res.get("line_coverage", 0.0)
                    branch_cov = test_res.get("branch_coverage", 0.0)
                    has_comp_errors = test_res.get(
                        "has_compilation_errors", False
                    ) or retry.get("has_compilation_errors", False)
                    test_info = test_res.get("test_info", {})
                    test_validi = test_info.get("valid_tests", []) or test_res.get(
                        "valid_tests", []
                    )
                    test_non_validi = test_info.get(
                        "invalid_tests", []
                    ) or test_res.get("invalid_tests", [])
                    
                    # Extract failed (assert) and runtime error tests separately from test_info
                    if not failed_only and not error_names:
                        failed_only = test_info.get("failed_assert_tests", [])
                        error_names = test_info.get("runtime_error_tests", [])
                    
                    # If still not available, try to deduce from test_non_validi
                    if (
                        not failed_only
                        and not error_names
                        and test_non_validi
                    ):
                        # Cannot distinguish, so put everything in failed
                        failed_only = test_non_validi
                    
                    # If we still don't have the names but know there are failed tests,
                    # calculate the number of failed tests from the difference
                    tests_failed_calc = tests_total - tests_passed
                    if (
                        not failed_only
                        and not error_names
                        and tests_failed_calc > 0
                    ):
                        # We don't have the names, but we know there are failed tests
                        # We'll use a placeholder to indicate failed tests without names
                        failed_only = ["(failed tests without name)"] * tests_failed_calc if tests_failed_calc > 0 else []
                
                # Override common variables if present in the retry
                if retry.get("refinement_prompt"):
                    refinement_prompt = retry.get("refinement_prompt")
                if retry.get("compilation_errors"):
                    errori_comp = retry.get("compilation_errors")
                    righe_non_coperte = retry.get("righe_non_coperte", [])
                
                # Always show refinement_prompt if present (expandable with details)
                # The refinement prompt is always shown because it represents what was passed to the LLM
                show_refinement_prompt = (
                    refinement_prompt is not None and len(refinement_prompt.strip()) > 0
                )
                
                # Generate HTML based on retry type
                if retry_type in ["refinement", "initial_method_generation"]:
                    # For refinement, show both original and regenerated
                    overall_similarity = retry.get("overall_similarity", 0.0) or 0.0
                    passes_threshold = retry.get("passes_threshold", False)
                    metriche_sim = retry.get("similarity_metrics", {})

                    # Calculate colors for original method progress bars
                    line_cov_color_orig = (
                        "#28a745"
                        if line_cov_orig >= 70
                        else "#ffc107"
                        if line_cov_orig >= 40
                        else "#dc3545"
                    )
                    branch_cov_color_orig = (
                        "#28a745"
                        if branch_cov_orig >= 70
                        else "#ffc107"
                        if branch_cov_orig >= 40
                        else "#dc3545"
                    )

                    # Calculate border color and threshold text
                    border_color = "#28a745" if passes_threshold else "#ffc107"
                    soglia_text = (
                        " (Threshold reached)"
                        if passes_threshold
                        else " (Below threshold)"
                    )
                    
                    html += f"""
                        <div class="code-box" style="margin-bottom: 20px; border-left: 5px solid {border_color};">
                            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
                                <h3>{retry_label}</h3>
                                <span style="color: #666; font-size: 0.9em;">{timestamp}</span>
                            </div>
                            
                            <div style="margin: 10px 0; padding: 10px; background: #e7f3ff; border-radius: 5px; border-left: 4px solid #2196F3;">
                                <strong style="color: #1976D2;"> Similarity: {overall_similarity:.4f} {soglia_text}</strong>
                            </div>
"""
                    # Calculate success for original method
                    success_orig = tests_passed_orig == tests_total_orig and tests_total_orig > 0
                            
                    html += f"""
                            <div style="margin: 15px 0;">
                                <h4 style="color: #28a745; margin-bottom: 10px;">✓ Original Method</h4>
                                <div style="margin: 10px 0;">
                                    <strong>Status:</strong> 
                                    <span class="pass-badge {"success" if success_orig else "fail"}" style="margin-left: 10px;">
                                        {"✓ Passed" if success_orig else "✗ Failed"}
                                    </span>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Test Results:</strong>
                                    <div style="margin-top: 5px;">
                                        <span style="padding: 5px 10px; border-radius: 5px; background: #d4edda; color: #155724; margin-right: 10px;">
                                            ✓ Passed: {tests_passed_orig}
                                        </span>
                                        <span style="padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24; margin-right: 10px;">
                                            ✗ Failed: {len(failed_only_orig) + len(error_names_orig)}
                                        </span>
                                        <span style="padding: 5px 10px; border-radius: 5px; background: #fff3cd; color: #856404;">
                                             Total: {tests_total_orig}
                                        </span>
                                    </div>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Test:</strong> {tests_passed_orig}/{tests_total_orig}
                                    <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                        <div class="progress-fill" style="width: {(tests_passed_orig / tests_total_orig * 100) if tests_total_orig > 0 else 0}%; font-size: 0.8em;">
                                            {tests_passed_orig}/{tests_total_orig}
                                        </div>
                                    </div>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Line Coverage:</strong> {line_cov_orig:.2f}%
                                    <div class="progress-bar" style="margin-top: 5px; margin-bottom: 10px; height: 20px;">
                                        <div class="progress-fill" style="width: {line_cov_orig}%; background: {line_cov_color_orig}; font-size: 0.8em;">
                                            {line_cov_orig:.1f}%
                                        </div>
                                    </div>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Branch Coverage:</strong> {branch_cov_orig:.2f}%
                                    <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                        <div class="progress-fill" style="width: {branch_cov_orig}%; background: {branch_cov_color_orig}; font-size: 0.8em;">
                                            {branch_cov_orig:.1f}%
                                        </div>
                                    </div>
                                </div>
"""
                    # Show passed, failed (assert) and runtime error tests with the same style as "Final Test Results"
                    # Always show passed tests if there are any, even when there are no failed tests
                    if test_validi_orig:
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #d4edda; border-radius: 5px; border-left: 4px solid #28a745;">
                                <strong style="color: #155724;">✓ Passed Tests ({len(test_validi_orig)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in test_validi_orig:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #155724;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                    elif tests_passed_orig > 0 and tests_total_orig > 0:
                        # If we don't have the list of valid tests but know some passed
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #d4edda; border-radius: 5px; border-left: 4px solid #28a745;">
                                <strong style="color: #155724;">✓ Passed Tests: {tests_passed_orig} out of {tests_total_orig}</strong>
                            </div>
"""
                    if failed_only_orig:
                        # Filtra "UnknownTest" dalla lista
                        failed_only_orig_filtered = [
                            t for t in failed_only_orig if t != "UnknownTest"
                        ]
                        if failed_only_orig_filtered:
                            html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #fff3cd; border-radius: 5px; border-left: 4px solid #ffc107;">
                                <strong style="color: #856404;"> Tests with Failed Assertions ({len(failed_only_orig_filtered)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                            for test_name in failed_only_orig_filtered:
                                html += f"""
                                    <li style="margin-bottom: 5px; color: #856404;">{test_name}</li>
"""
                            html += """
                                </ul>
                            </div>
"""
                    if error_names_orig:
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">💥 Tests with Runtime Errors ({len(error_names_orig)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in error_names_orig:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                    # Fallback: if we couldn't distinguish, show test_non_validi
                    if (
                        not failed_only_orig
                        and not error_names_orig
                        and test_non_validi_orig
                    ):
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">✗ Failed Tests ({len(test_non_validi_orig)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in test_non_validi_orig:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                    # Calculate colors for progress bars
                    line_cov_color_regen = (
                        "#28a745"
                        if line_cov_regen >= 70
                        else "#ffc107"
                        if line_cov_regen >= 40
                        else "#dc3545"
                    )
                    branch_cov_color_regen = (
                        "#28a745"
                        if branch_cov_regen >= 70
                        else "#ffc107"
                        if branch_cov_regen >= 40
                        else "#dc3545"
                    )

                    # Calculate success for regenerated method
                    success_regen = tests_passed_regen == tests_total_regen and tests_total_regen > 0
                    
                    html += f"""
                            </div>
                            
                            <div style="margin: 15px 0;">
                                <h4 style="color: #dc3545; margin-bottom: 10px;">🔄 Regenerated Method</h4>
                                <div style="margin: 10px 0;">
                                    <strong>Status:</strong> 
                                    <span class="pass-badge {"success" if success_regen else "fail"}" style="margin-left: 10px;">
                                        {"✓ Passed" if success_regen else "✗ Failed"}
                                    </span>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Test Results:</strong>
                                    <div style="margin-top: 5px;">
                                        <span style="padding: 5px 10px; border-radius: 5px; background: #d4edda; color: #155724; margin-right: 10px;">
                                            ✓ Passed: {tests_passed_regen}
                                        </span>
                                        <span style="padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24; margin-right: 10px;">
                                            ✗ Failed: {len(failed_only_regen) + len(error_names_regen)}
                                        </span>
                                        <span style="padding: 5px 10px; border-radius: 5px; background: #fff3cd; color: #856404;">
                                             Total: {tests_total_regen}
                                        </span>
                                    </div>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Test:</strong> {tests_passed_regen}/{tests_total_regen}
                                    <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                        <div class="progress-fill" style="width: {(tests_passed_regen / tests_total_regen * 100) if tests_total_regen > 0 else 0}%; font-size: 0.8em;">
                                            {tests_passed_regen}/{tests_total_regen}
                                        </div>
                                    </div>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Line Coverage:</strong> {line_cov_regen:.2f}%
                                    <div class="progress-bar" style="margin-top: 5px; margin-bottom: 10px; height: 20px;">
                                        <div class="progress-fill" style="width: {line_cov_regen}%; background: {line_cov_color_regen}; font-size: 0.8em;">
                                            {line_cov_regen:.1f}%
                                        </div>
                                    </div>
                                </div>
                                <div style="margin: 10px 0;">
                                    <strong>Branch Coverage:</strong> {branch_cov_regen:.2f}%
                                    <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                        <div class="progress-fill" style="width: {branch_cov_regen}%; background: {branch_cov_color_regen}; font-size: 0.8em;">
                                            {branch_cov_regen:.1f}%
                                        </div>
                                    </div>
                                </div>
"""
                    # Show passed, failed (assert) and runtime error tests with the same style as "Final Test Results"
                    # Always show passed tests if there are any, even when there are no failed tests
                    if test_validi_regen:
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #d4edda; border-radius: 5px; border-left: 4px solid #28a745;">
                                <strong style="color: #155724;">✓ Passed Tests ({len(test_validi_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in test_validi_regen:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #155724;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                    elif tests_passed_regen > 0 and tests_total_regen > 0:
                        # If we don't have the list of valid tests but know some passed
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #d4edda; border-radius: 5px; border-left: 4px solid #28a745;">
                                <strong style="color: #155724;">✓ Passed Tests: {tests_passed_regen} out of {tests_total_regen}</strong>
                            </div>
"""
                    if failed_only_regen:
                        # Filtra "UnknownTest" dalla lista
                        failed_only_regen_filtered = [
                            t for t in failed_only_regen if t != "UnknownTest"
                        ]
                        if failed_only_regen_filtered:
                            html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #fff3cd; border-radius: 5px; border-left: 4px solid #ffc107;">
                                <strong style="color: #856404;"> Tests with Failed Assertions ({len(failed_only_regen_filtered)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                            for test_name in failed_only_regen_filtered:
                                html += f"""
                                    <li style="margin-bottom: 5px; color: #856404;">{test_name}</li>
"""
                            html += """
                                </ul>
                            </div>
"""
                    if error_names_regen:
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">💥 Tests with Runtime Errors ({len(error_names_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in error_names_regen:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                    # Fallback: if we couldn't distinguish, show test_non_validi
                    if (
                        not failed_only_regen
                        and not error_names_regen
                        and test_non_validi_regen
                    ):
                        html += f"""
                            <div style="margin-top: 15px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">✗ Failed Tests ({len(test_non_validi_regen)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in test_non_validi_regen:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                    # Prepare data for method comparison
                    metodo_originale_retry = html_module.escape(
                        retry.get("original_method", "N/A")
                    )
                    metodo_rigenerato_retry = html_module.escape(
                        retry.get("regenerated_method", "N/A")
                    )

                    # Calculate color for overall similarity
                    overall_color = (
                        "#28a745"
                        if overall_similarity >= 0.7
                        else "#ffc107"
                        if overall_similarity >= 0.5
                        else "#dc3545"
                    )

                    # Build similarity metrics HTML
                    metriche_html = ""
                    if metriche_sim.get("ast_similarity") is not None:
                        metriche_html += f'<div style="padding: 10px; background: white; border-radius: 5px; border: 1px solid #ddd;"><strong style="color: #666;">AST Similarity:</strong><div style="font-size: 1.2em; font-weight: bold; color: #333;">{metriche_sim.get("ast_similarity", 0.0):.4f}</div></div>'
                    if metriche_sim.get("embedding_similarity") is not None:
                        metriche_html += f'<div style="padding: 10px; background: white; border-radius: 5px; border: 1px solid #ddd;"><strong style="color: #666;">UniXcoder Similarity:</strong><div style="font-size: 1.2em; font-weight: bold; color: #333;">{metriche_sim.get("embedding_similarity", 0.0):.4f}</div></div>'
                    if metriche_sim.get("crystalbleu_similarity") is not None:
                        metriche_html += f'<div style="padding: 10px; background: white; border-radius: 5px; border: 1px solid #ddd;"><strong style="color: #666;">CrystalBLEU:</strong><div style="font-size: 1.2em; font-weight: bold; color: #333;">{metriche_sim.get("crystalbleu_similarity", 0.0):.4f}</div></div>'
                    if metriche_sim.get("string_similarity") is not None:
                        metriche_html += f'<div style="padding: 10px; background: white; border-radius: 5px; border: 1px solid #ddd;"><strong style="color: #666;">String Similarity:</strong><div style="font-size: 1.2em; font-weight: bold; color: #333;">{metriche_sim.get("string_similarity", 0.0):.4f}</div></div>'
                    if metriche_sim.get("token_similarity") is not None:
                        metriche_html += f'<div style="padding: 10px; background: white; border-radius: 5px; border: 1px solid #ddd;"><strong style="color: #666;">Token Similarity:</strong><div style="font-size: 1.2em; font-weight: bold; color: #333;">{metriche_sim.get("token_similarity", 0.0):.4f}</div></div>'

                    html += f"""
                            </div>
                            
                            <div style="margin: 20px 0;">
                                <h4 style="color: #333; margin-bottom: 15px; border-bottom: 2px solid #667eea; padding-bottom: 5px;"> Original vs Regenerated Method Comparison ({retry_label})</h4>
                                <div class="code-container" style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                                    <div class="code-box original" style="border: 2px solid #28a745;">
                                        <h4 style="margin-top: 0; color: #28a745; padding: 10px; background: #d4edda;">✓ Original Method</h4>
                                        <pre style="max-height: 400px; overflow-y: auto; background: #2d2d2d; color: #f8f8f2; padding: 15px; border-radius: 5px; font-family: 'Consolas', 'Monaco', monospace; font-size: 0.9em; line-height: 1.5; white-space: pre-wrap; word-wrap: break-word;"><code style="font-size: 0.9em; color: #f8f8f2;">{metodo_originale_retry}</code></pre>
                                    </div>
                                    <div class="code-box regenerated" style="border: 2px solid #dc3545;">
                                        <h4 style="margin-top: 0; color: #dc3545; padding: 10px; background: #f8d7da;">🔄 Regenerated Method ({retry_label})</h4>
                                        <pre style="max-height: 400px; overflow-y: auto; background: #2d2d2d; color: #f8f8f2; padding: 15px; border-radius: 5px; font-family: 'Consolas', 'Monaco', monospace; font-size: 0.9em; line-height: 1.5; white-space: pre-wrap; word-wrap: break-word;"><code style="font-size: 0.9em; color: #f8f8f2;">{metodo_rigenerato_retry}</code></pre>
                                    </div>
                                </div>
                            </div>
                            
                            <div style="margin: 15px 0; padding: 15px; background: #e7f3ff; border-radius: 5px; border-left: 4px solid #2196F3;">
                                <h4 style="margin-top: 0; color: #1976D2;"> Similarity Metrics ({retry_label})</h4>
                                <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 15px; margin-top: 10px;">
                                    <div style="padding: 10px; background: white; border-radius: 5px; border: 1px solid #ddd;">
                                        <strong style="color: #666;">Overall Similarity:</strong>
                                        <div style="font-size: 1.5em; font-weight: bold; color: {overall_color};">{overall_similarity:.4f}</div>
                                    </div>
                                    {metriche_html}
                                </div>
                            </div>
"""
                elif retry_type == "repair_after_refinement":
                    # Simplified display for repair after refinement
                    repair_iter = retry.get("repair_iteration", "?")
                    after_ref_iter = retry.get("after_refinement_iteration", "?")
                    errori_comp_repair = retry.get("compilation_errors", "")
                    has_comp_errors_repair = retry.get("has_compilation_errors", False)
                    
                    border_color_repair = "#dc3545" if has_comp_errors_repair else "#28a745"
                    
                    html += f"""
                        <div class="code-box" style="margin-bottom: 15px; border-left: 5px solid {border_color_repair}; padding: 15px;">
                            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
                                <h4 style="margin: 0; color: #dc3545;"> Repair {repair_iter} (after Refinement {after_ref_iter})</h4>
                                <span class="pass-badge {"fail" if has_comp_errors_repair else "success"}">
                                    {"WARNING: Compilation Errors" if has_comp_errors_repair else "✓ Compilation OK"}
                                </span>
                            </div>
"""
                    if errori_comp_repair:
                        html += f"""
                            <div style="margin-top: 10px; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">📋 Compilation Errors:</strong>
                                <pre style="margin-top: 10px; white-space: pre-wrap; word-wrap: break-word; background: #2d2d2d; color: #f8f8f2; padding: 10px; border-radius: 5px; max-height: 200px; overflow-y: auto; font-size: 0.85em;">{html_module.escape(errori_comp_repair)}</pre>
                            </div>
"""
                    html += """
                        </div>
"""
                else:
                    # For repair, initial_test_execution, initial_regenerated_test_execution
                    html += f"""
                        <div class="code-box" style="margin-bottom: 20px; border-left: 5px solid {"#28a745" if tests_passed == tests_total and not has_comp_errors else "#ffc107" if tests_passed > 0 else "#dc3545"};">
                            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px;">
                                <h3>{retry_label}</h3>
                                <span style="color: #666; font-size: 0.9em;">{timestamp}</span>
                            </div>
                            
                            <div style="margin: 10px 0;">
                                <strong>Test Results:</strong>
                                <div style="margin-top: 5px;">
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #d4edda; color: #155724; margin-right: 10px;">
                                        ✓ Passed: {tests_passed}
                                    </span>
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #f8d7da; color: #721c24; margin-right: 10px;">
                                        ✗ Failed: {tests_failed}
                                    </span>
                                    <span style="padding: 5px 10px; border-radius: 5px; background: #fff3cd; color: #856404;">
                                         Total: {tests_total}
                                    </span>
                                </div>
                            </div>
                            
                            <div style="margin: 10px 0;">
                                <strong>Coverage:</strong> Line {line_cov:.2f}% | Branch {branch_cov:.2f}%{soglia_display}
                                <div class="progress-bar" style="margin-top: 5px; height: 20px;">
                                    <div class="progress-fill" style="width: {line_cov}%; background: {"#28a745" if (soglia_coverage and line_cov >= soglia_coverage) or (not soglia_coverage and line_cov >= 80) else "#ffc107" if line_cov >= 50 else "#dc3545"}; font-size: 0.8em;">
                                        {line_cov:.1f}%
                                    </div>
                                </div>
                            </div>
                            
                            {f'<div style="margin: 10px 0;"><strong>Valid Tests ({len(test_validi)}):</strong> {", ".join(test_validi) if test_validi else "None"}</div>' if test_validi else ""}
                            {f'<div style="margin: 10px 0;"><strong>Invalid Tests ({len(test_non_validi)}):</strong> {", ".join(test_non_validi) if test_non_validi else "None"}</div>' if test_non_validi else ""}
"""
                
                if has_comp_errors:
                    html += f"""
                            <div style="margin: 10px 0; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">WARNING: Compilation Errors Detected</strong>
                            </div>
"""
                
                if failed_only:
                    # Filtra "UnknownTest" dalla lista
                    failed_only_filtered = [
                        t for t in failed_only if t != "UnknownTest"
                    ]
                    if failed_only_filtered:
                        html += f"""
                            <div style="margin: 10px 0; padding: 10px; background: #fff3cd; border-radius: 5px; border-left: 4px solid #ffc107;">
                                <strong style="color: #856404;"> Tests with Failed Assertions ({len(failed_only_filtered)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                        for test_name in failed_only_filtered:
                            html += f"""
                                    <li style="margin-bottom: 5px; color: #856404;">{test_name}</li>
"""
                        html += """
                                </ul>
                            </div>
"""
                
                if error_names:
                    html += f"""
                            <div style="margin: 10px 0; padding: 10px; background: #f8d7da; border-radius: 5px; border-left: 4px solid #dc3545;">
                                <strong style="color: #721c24;">💥 Tests with Runtime Errors ({len(error_names)}):</strong>
                                <ul style="margin-top: 10px; padding-left: 20px;">
"""
                    for test_name in error_names:
                        html += f"""
                                    <li style="margin-bottom: 5px; color: #721c24;">{test_name}</li>
"""
                    html += """
                                </ul>
                            </div>
"""
                
                if errori_comp:
                    html += f"""
                            <div style="margin: 10px 0;">
                                <details style="cursor: pointer;">
                                    <summary style="font-weight: bold; color: #856404; padding: 10px; background: #fff3cd; border-radius: 5px;">
                                        📋 Extracted Failure/Runtime Errors
                                    </summary>
                                    <div style="margin-top: 10px; padding: 15px; background: #f8f9fa; border-radius: 5px;">
                                        <pre style="white-space: pre-wrap; word-wrap: break-word; max-height: 300px; overflow-y: auto;">{html_module.escape(errori_comp)}</pre>
                                    </div>
                                </details>
                            </div>
"""
                
                if show_refinement_prompt:
                    html += f"""
                            <div style="margin: 10px 0;">
                                <details style="cursor: pointer;">
                                    <summary style="font-weight: bold; color: #856404; padding: 10px; background: #fff3cd; border-radius: 5px;">
                                         Refinement Prompt
                                    </summary>
                                    <div style="margin-top: 10px; padding: 15px; background: #f8f9fa; border-radius: 5px;">
                                        <pre style="white-space: pre-wrap; word-wrap: break-word; max-height: 400px; overflow-y: auto;">{html_module.escape(refinement_prompt)}</pre>
                                    </div>
                                </details>
                            </div>
"""
                
                html += """
                        </div>
"""
            
            html += """
                    </div>
"""
        
        # DO NOT show the "Original vs Regenerated Method Comparison" section here anymore
        # because it is already shown at the top as "Final Method Comparison"
        
        # Dependency selection section (context passed to the model)
        if selezione_info and selezione_info.get("active", False):
            metodo_selezione = selezione_info.get("method", "N/A")
            classi_selezionate = selezione_info.get("selected_classes", [])
            top_k = selezione_info.get("top_k", "N/A")
            
            html += f"""
                    <div class="selezione-section">
                        <h4>Context Passed to Model (Dependency Selection)</h4>
                        <div style="margin-bottom: 10px;">
                            <strong>Selection Method:</strong> {metodo_selezione.upper() if metodo_selezione != "N/A" else "N/A"}
                            {f" | <strong>Top-K:</strong> {top_k}" if top_k != "N/A" else ""}
                        </div>
                        {f"<div><strong>Included Classes ({len(classi_selezionate)}):</strong></div>" if classi_selezionate else ""}
                        <div class="selezione-classes">
"""
            for cls_info in classi_selezionate:
                classe_nome = cls_info.get("class_name", "N/A")
                score = cls_info.get("score")
                rank = cls_info.get("rank")
                badge_text = classe_nome
                if rank is not None:
                    badge_text += f" (#{rank})"
                if score is not None:
                    badge_text += f" [{score:.3f}]"
                html += f"""
                            <span class="selezione-class-badge">{html_module.escape(badge_text)}</span>
"""
            html += """
                        </div>
                    </div>
"""
        
        # Prompts used - always show initial prompts if phases were executed
        prompt_da_mostrare = []
        if prima_fase and prompt_test:
            prompt_da_mostrare.append(
                ("Test Generation Prompt", prompt_test, f"prompt_test_{versione}")
            )
        if seconda_fase and prompt_rigenera:
            prompt_da_mostrare.append(
                (
                    "Method Regeneration Prompt",
                    prompt_rigenera,
                    f"prompt_regen_{versione}",
                )
            )
        
        if prompt_da_mostrare:
            html += f"""
                    <div class="prompt-section">
                        <h3> Prompts Used</h3>
"""
            for label, content, modal_id in prompt_da_mostrare:
                # Show only a preview (first 500 characters)
                preview = content[:500] + "..." if len(content) > 500 else content
                html += f"""
                        <div class="prompt-box">
                            <div class="prompt-label">
                                {label}:
                                <button class="view-prompt-btn" onclick="showFullPrompt('{modal_id}')">View Full Prompt</button>
                            </div>
                            <div class="prompt-content">{html_module.escape(preview)}</div>
                        </div>
                        <div id="{modal_id}" class="full-prompt-modal">
                            <div class="full-prompt-content">
                                <div class="full-prompt-header">
                                    <h3>{label}</h3>
                                    <button class="close-btn" onclick="closeFullPrompt('{modal_id}')">&times; Close</button>
                                </div>
                                <pre style="background: #2d2d2d; color: #f8f8f2; padding: 15px; border-radius: 5px; overflow-x: auto; white-space: pre-wrap; font-family: 'Consolas', 'Monaco', monospace;">{html_module.escape(content)}</pre>
                            </div>
                        </div>
"""
            html += """
                    </div>
"""

        # Tests generated in the first phase (if present)
        if test_prima_fase:
            test_id = f"test_content_{versione}"
            html += f"""
                    <div class="code-section">
                        <h3>🧪 Generated Tests (first phase)</h3>
                        <div class="code-box">
                            <div class="test-expandable" id="{test_id}">
                                <pre><code>{html_module.escape(test_prima_fase)}</code></pre>
                            </div>
                            <button class="expand-test-btn" onclick="toggleTestExpand('{test_id}')">Expand/Collapse Tests</button>
                        </div>
                    </div>
            """
        
        html += f"""
                    <div class="timestamp">
                        Timestamp: {esperimento.get("timestamp", "N/A")}
                    </div>
                </div>
"""
    
    # Charts
    if include_charts and len(versioni) > 1:
        html += (
            """
            <div class="chart-container">
                <h3>Similarity Trend</h3>
                <canvas id="similarityChart"></canvas>
            </div>
            
            <script>
                const ctx = document.getElementById('similarityChart').getContext('2d');
                new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: """
            + json.dumps([v.replace("version_", "v") for v in versioni])
            + """,
                        datasets: [
                            {
                                label: 'Overall Similarity',
                                data: """
            + json.dumps(similarita_data)
            + """,
                                borderColor: 'rgb(102, 126, 234)',
                                backgroundColor: 'rgba(102, 126, 234, 0.1)',
                                tension: 0.4,
                                fill: true
                            },
                            {
                                label: 'AST Similarity',
                                data: """
            + json.dumps(ast_data)
            + """,
                                borderColor: 'rgb(118, 75, 162)',
                                backgroundColor: 'rgba(118, 75, 162, 0.1)',
                                tension: 0.4,
                                fill: true
                            },
                            {
                                label: 'CrystalBLEU',
                                data: """
            + json.dumps(crystalbleu_data)
            + """,
                                borderColor: 'rgb(255, 99, 132)',
                                backgroundColor: 'rgba(255, 99, 132, 0.1)',
                                tension: 0.4,
                                fill: true
                            },
                            {
                                label: 'UniXcoder Similarity',
                                data: """
            + json.dumps(embedding_data)
            + """,
                                borderColor: 'rgb(54, 162, 235)',
                                backgroundColor: 'rgba(54, 162, 235, 0.1)',
                                tension: 0.4,
                                fill: true
                            }
                        ]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        aspectRatio: 2,
                        scales: {
                            y: {
                                beginAtZero: true,
                                min: 0,
                                max: 1.0,
                                ticks: {
                                    stepSize: 0.05,
                                    callback: function(value) {
                                        return (value * 100).toFixed(0) + '%';
                                    }
                                },
                                grid: {
                                    drawBorder: true
                                }
                            },
                            x: {
                                grid: {
                                    display: false
                                }
                            }
                        },
                        plugins: {
                            legend: {
                                position: 'top',
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(context) {
                                        return context.dataset.label + ': ' + (context.parsed.y * 100).toFixed(2) + '%';
                                    }
                                }
                            }
                        }
                    }
                });
            </script>
"""
        )
    
    html += """
            </div>
        </div>
    </div>
    <script>
        function showFullPrompt(modalId) {
            document.getElementById(modalId).classList.add('show');
        }
        
        function closeFullPrompt(modalId) {
            document.getElementById(modalId).classList.remove('show');
        }
        
        function toggleTestExpand(testId) {
            const element = document.getElementById(testId);
            const btn = element.nextElementSibling;
            if (element.classList.contains('expanded')) {
                element.classList.remove('expanded');
                btn.textContent = 'Expand Tests';
            } else {
                element.classList.add('expanded');
                btn.textContent = 'Collapse Tests';
            }
        }
        
        // Close modal by clicking outside
        window.onclick = function(event) {
            if (event.target.classList.contains('full-prompt-modal')) {
                event.target.classList.remove('show');
            }
        }
    </script>
</body>
</html>
"""
    
    return html


def metrica_peso(metriche: Dict, key: str) -> str:
    try:
        w = metriche.get("weights", {}).get(key)
        return f"{float(w):.2f}" if w is not None else "-"
    except Exception:
        return "-"
