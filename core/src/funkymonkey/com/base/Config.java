package funkymonkey.com.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Config {

    private Preferences prefs;

    /**
     * Config - конструктор
     */
    public Config () {
        this.prefs = Gdx.app.getPreferences( "Symbols" );
        this.addSymbolConfiguration();
    }

    public float getSymbCnf ( String key ) {
        return this.prefs.getFloat( key );
    }

    private void addSymbolConfiguration () {
        this.prefs.putFloat("startX", -0.5655f );
        this.prefs.putFloat("startY", -0.291f );
        this.prefs.putFloat("offsetX", 0.233f );
        this.prefs.putFloat("offsetY", 0.231f );
    }
}
