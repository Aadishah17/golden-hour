import inspect
import graphify.export

print("Inspecting to_html:")
try:
    print(inspect.signature(graphify.export.to_html))
except Exception as e:
    print(f"Error inspecting to_html: {e}")

print("
Inspecting generate_html:")
try:
    print(inspect.signature(graphify.export.generate_html))
except Exception as e:
    print(f"Error inspecting generate_html: {e}")
