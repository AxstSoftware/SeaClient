package io.github.sdxqw;

public class StartClient {
    /**
     * This string is the tweaker, should be the same name of "mixins.tweaker.json"
     * @see io.github.sdxqw.launch.Tweaker line 44 if you don't want that string!
     */
    public final String tweaker = "tweaker";

    public static StartClient instance = new StartClient();

    public void onInitClient() {
        System.out.println("work lol");
    }
}
