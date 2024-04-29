import json

# Load the JSON file
with open('lipas_kaikki_pisteet.json', 'r', encoding='utf-8') as f:
    data = json.load(f)

# Specify the "tyyppi_nim" values you want to remove
remove_values = ['Kalastusalue/-paikka', 'Tupa', 'Laavu, kota tai kammi', 'Ruoanlaittopaikka', 'Rantautumispaikka', 'Telttailu ja leiriytyminen', 'Kiihdytysrata', 'Purjehdusalue', 'Ulkoilumaja/hiihtomaja', 
                 'Jokamies- ja rallicross-rata', 'Huoltorakennukset', 'Veneilyn palvelupaikka', 'Ampumarata', 'Sisäampumarata', 'Ravirata', 'Kylpylä', 'Ampumaurheilukeskus', 
                 'Harjoitushyppyrimäki', 'Hyppyrimäki', 'Soutustadion', 'Hiihtotunneli', 'Vesihiihtoalue', 'Jääspeedway-rata', 'Koskimelontakeskus', 'Alamäkiluistelurata',
                 'Rullakiekkokenttä', 'Moottorirata', 'Luontotorni', 'Opastuspiste' ]

# Filter out the features with the specified "tyyppi_nim" values
data['features'] = [feature for feature in data['features'] if feature['properties']['tyyppi_nim'] not in remove_values]

# Write the modified data back to the JSON file
with open('lipas_kaikki_pisteetC2.json', 'w', encoding='utf-8') as f:
    json.dump(data, f, ensure_ascii=False)