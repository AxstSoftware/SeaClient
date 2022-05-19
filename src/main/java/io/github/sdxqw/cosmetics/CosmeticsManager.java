package io.github.sdxqw.cosmetics;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonParser;
import io.github.sdxqw.database.Database;
import io.github.sdxqw.utils.interfaces.ILogger;
import net.minecraft.util.ResourceLocation;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CosmeticsManager {

    public static final HashMap<String, CosmeticData> cosmetics;

    public static void init() {
        ILogger.info("Initializing connection..." );
        try {
            final Connection connection = DriverManager.getConnection(Database.INSTANCE.url);
            final PreparedStatement statement = connection.prepareStatement( "SELECT * FROM cosmetics" );
            try {
                final ResultSet result = statement.executeQuery();
                try {
                    if (result != null) {
                        while (result.next()) {
                            final List<CosmeticList> cosmeticList = Lists.newArrayList();
                            for (final String cosmetic : result.getObject("cape_name").toString().split(",")) {
                                cosmeticList.add(CosmeticList.valueOf(cosmetic));
                            }
                            final String player = result.getString("player_name");
                            final HashMap<String, CosmeticData> cosmetics = CosmeticsManager.cosmetics;
                            final String cape = result.getString("cape_name");
                            ResourceLocation capeTexture;
                            if (cosmeticList.contains(CosmeticList.CAPES)) {
                                final StringBuilder sb = new StringBuilder();
                                capeTexture = new ResourceLocation(sb.append("seaclient/capes/").append(getCapeName(cape)).append(".png").toString());
                            }
                            else {
                                capeTexture = null;
                            }
                            cosmetics.put(cape, new CosmeticData( cosmeticList, player, capeTexture));
                        }
                    }
                }
                finally {
                    if (Collections.singletonList(result).get(0) != null) {
                        if (result != null) {
                            result.close();
                        }
                    }
                }
            }
            finally {
                if (Collections.singletonList(statement).get(0) != null) {
                    statement.close();
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ILogger.info("Cosmetics fetched!" );
    }

    public static boolean hasCape(final String uuid) {
        return CosmeticsManager.cosmetics.containsKey(uuid) && CosmeticsManager.cosmetics.get(uuid).getCosmetics().stream().anyMatch(cosmetic -> cosmetic == CosmeticList.CAPES);
    }

    public static String getCapeName(final String string) {
        Connection connection;
        PreparedStatement prepareStatement;
        ResultSet resultSet;
        try {
            connection = Database.INSTANCE.initConnection();
            prepareStatement = connection.prepareStatement("SELECT * FROM cosmetics WHERE cape_name = '"+string+"';");

            resultSet = prepareStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("cape_name").equalsIgnoreCase(string.toLowerCase())) {
                    return resultSet.getString("cape_name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
        return null;
    }

    static {
        cosmetics = Maps.newHashMap();
    }

    enum CosmeticList
    {
        CAPES,
        WINGS,
        BACK_TOOL
    }
}
