import json

# Load the JSON file
with open('lipas_kaikki_pisteetC2.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

# Use a dictionary to count "tyyppi_nim" values
tyyppi_nim_counts = {}

# Iterate over the features and increment the count for each "tyyppi_nim" value
for feature in data['features']:
    tyyppi_nim = feature['properties']['tyyppi_nim']
    if tyyppi_nim in tyyppi_nim_counts:
        tyyppi_nim_counts[tyyppi_nim] += 1
    else:
        tyyppi_nim_counts[tyyppi_nim] = 1

# Print the counts for each "tyyppi_nim" value
for tyyppi_nim, count in sorted(tyyppi_nim_counts.items(), key=lambda item: item[1], reverse=True):
    print(f'{tyyppi_nim}: {count}')