import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

# Title
st.set_page_config(page_title="Player Insights", layout="centered")
st.title("🏏 IPL Player Insights")

# Load Data
@st.cache_data
def load_data():
    auction = pd.read_csv("datasets/auction_data/ipl_2025_auction_players.csv")
    stats = pd.read_csv("datasets/player_data/cricket_data_2025.csv")

    # Clean names
    auction['Players'] = auction['Players'].str.strip().str.lower()
    stats['Player_Name'] = stats['Player_Name'].str.strip().str.lower()

    return auction, stats

auction_data, player_stats = load_data()

# Input from user
# player_input = st.text_input("Enter Player Name")
player_list = sorted(auction_data['Players'].unique())
selected_player = st.selectbox("Select a Player:", player_list)

#Filter data
auction_info = auction_data[auction_data['Players'] == selected_player]
stats_info = player_stats[player_stats['Player_Name'] == selected_player]

# if player_input:
#     name = player_input.strip().lower()
#     auction_info = auction_data[auction_data['Players'] == name]
    
if auction_info.empty:
    st.error(f"❌ Player  not found in auction data.")
else:
    info = auction_info.iloc[0]
    st.markdown(f"### 🔍 Player: {selected_player}")
    st.write(f"👕 **Team**: {info['Team']}")
    st.write(f"🏏 **Type**: {info['Type']}")
    st.write(f"💰 **Last Year Sold Price**: ₹{info['Sold']} Cr")
    
    # Stats
    if stats_info.empty:
        st.warning("📊 No stats available for this player.")
    else:
        st.markdown("### 📈 Performance Over Years")
        st.dataframe(stats_info)

        # stats = player_stats[player_stats['Player_Name'] == name]
        if "Year" in stats_info.columns and 'Runs_Scored' in stats_info.columns:
            stats_info['Year'] = stats_info['Year'].astype(str)
            fig, ax = plt.subplots(figsize=(8, 4))
            sns.barplot(data=stats_info, x='Year', y='Runs_Scored', ax = ax)
            ax.set_title("Yearly Runs")
            st.pyplot(fig)
        
        #ML Prediction - Linear Regression
        st.markdown("### 🔮 Price Prediction (ML)")
        try:
            df_model = stats_info[['Year', 'Runs_Scored']].dropna()
            df_model['Year'] = df_model['Year'].astype(int)
            X = df_model[['Year']]
            y = df_model['Runs_Scored']

            model = LinearRegression()
            model.fit(X, y)

            next_year = X['Year'].max() + 1
            predicted_runs = model.predict([[next_year]])[0]

            # Price prediction = simple ratio with runs (demo purpose)
            current_price = float(info['Sold'])
            last_runs = df_model[df_model['Year'] == X['Year'].max()]['Runs_Scored'].values[0]
            price_per_run = current_price / last_runs if last_runs > 0 else 0
            predicted_price = round(price_per_run * predicted_runs, 2)

            st.write(f"📅 Predicted Runs in {next_year}: **{int(predicted_runs)}**")
            st.write(f"💸 Predicted Price for {next_year}: **₹{predicted_price} Cr**")

        except Exception as e:
            st.warning("⚠️ Not enough data for prediction.")
            st.text(str(e))
