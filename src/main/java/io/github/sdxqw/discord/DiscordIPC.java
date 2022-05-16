package io.github.sdxqw.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import io.github.sdxqw.utils.interfaces.IHelper;
import org.json.JSONObject;

import java.time.OffsetDateTime;

public class DiscordIPC implements IPCListener {

    public static final DiscordIPC INSTANCE = new DiscordIPC();
    private IPCClient client;
    private boolean hasClient = true;

    public void init() {
        client = new IPCClient( 975835125591965776L );
        client.setListener( this );
        try {
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
            hasClient = false;
        }
    }

    public void update(String state, String details) {
        if (hasClient) {
            RichPresence.Builder builder = new RichPresence.Builder()
                    .setState( state )
                    .setDetails( details )
                    .setLargeImage( "large", "Sea Client" )
                    .setStartTimestamp( OffsetDateTime.now() );
            client.sendRichPresence( builder.build() );
        }
    }

    public void shutdown() {
        if (client != null && hasClient && client.getStatus() == PipeStatus.CONNECTED) {
            client.close();
        }
    }

    @Override
    public void onReady(IPCClient client) {
        RichPresence.Builder builder = new RichPresence.Builder()
                .setState( "IGN: " + IHelper.minecraft.getSession().getUsername() )
                .setDetails( "Minecraft 1.8.9 (Sea Client)" )
                .setLargeImage( "large", "Sea Client" )
                .setStartTimestamp( OffsetDateTime.now() );
        client.sendRichPresence( builder.build() );
    }

    @Override
    public void onClose(IPCClient client, JSONObject json) {

    }

    @Override
    public void onDisconnect(IPCClient client, Throwable t) {

    }
}
