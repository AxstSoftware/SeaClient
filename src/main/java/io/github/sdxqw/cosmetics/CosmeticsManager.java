package io.github.sdxqw.cosmetics;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonParser;
import io.github.sdxqw.database.Database;
import io.github.sdxqw.utils.interfaces.ILogger;
import net.minecraft.util.ResourceLocation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CosmeticsManager {

    public static final HashMap<String, CosmeticData> cosmetics;

    public static void init() {
        ILogger.info("Initializing connection..." );
        final Database.Connection connection = Database.INSTANCE.initConnection();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `cosmetics`");
            try {
                final ResultSet result = statement.executeQuery();
                try {
                    if (result != null) {
                        while (result.next()) {
                            final List<CosmeticList> cosmeticList = Lists.newArrayList();
                            for (final String cosmetic : result.getObject("id").toString().split(",")) {
                                cosmeticList.add(CosmeticList.valueOf(cosmetic));
                            }
                            Database.INSTANCE.insertCapes( "cape", "SuchSpeed" );
                            final String data = result.getString("data");
                            final HashMap<String, CosmeticData> cosmetics = CosmeticsManager.cosmetics;
                            final String string = result.getString("name");
                            ResourceLocation capeTexture;
                            if (cosmeticList.contains(CosmeticList.CAPES)) {
                                final StringBuilder sb = new StringBuilder();
                                capeTexture = new ResourceLocation(sb.append("seaclient/capes/").append(getCapeName(data)).append(".png").toString());
                            }
                            else {
                                capeTexture = null;
                            }
                            cosmetics.put(string, new CosmeticData( cosmeticList, data, capeTexture));
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

    public static String getCapeName(final String json) {
        return new JsonParser().parse(json).getAsJsonObject().get("cape").getAsString();
    }

    static {
        cosmetics = Maps.newHashMap();
    }

    enum CosmeticList
    {
        CAPES,
        WINGS,
        BACK_TOOL;
    }
}
