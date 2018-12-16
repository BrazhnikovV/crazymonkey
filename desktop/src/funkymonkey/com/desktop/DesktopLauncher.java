package funkymonkey.com.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import funkymonkey.com.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// устанавливаем размеры окна приложения
		config.height = 768;
		config.width  = 1024;

		// запрещаем изменять размер экрана игры
		config.resizable = false;

		new LwjglApplication( new MyGdxGame(), config );
	}
}
