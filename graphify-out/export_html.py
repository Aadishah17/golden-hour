import sys
import json
from pathlib import Path
import graphify.export

def main():
    graph_json_path = Path('graphify-out/graph.json')
    output_html_path = Path('graphify-out/graph.html')
    
    if not graph_json_path.exists():
        print("Error: graph.json not found. Did you run the build script?")
        sys.exit(1)

    print("Attempting HTML export...")
    
    # Try to_html first
    if hasattr(graphify.export, 'to_html'):
        try:
            print("Using to_html...")
            graphify.export.to_html(graph_json_path, output_html_path)
            print(f"HTML export successful: {output_html_path}")
            return
        except Exception as e:
            print(f"to_html failed: {e}")
    
    # Try generate_html as fallback
    if hasattr(graphify.export, 'generate_html'):
        try:
            print("Using generate_html...")
            graphify.export.generate_html(graph_json_path, output_html_path)
            print(f"HTML export successful: {output_html_path}")
            return
        except Exception as e:
            print(f"generate_html failed: {e}")
    
    print("Error: Could not find a suitable HTML export function in graphify.export.")
    print(f"Available functions: {dir(graphify.export)}")
    sys.exit(1)

if __name__ == '__main__':
    main()
