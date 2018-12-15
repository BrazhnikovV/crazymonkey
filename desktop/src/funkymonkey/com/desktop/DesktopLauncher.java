package funkymonkey.com.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import funkymonkey.com.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// устанавливаем размеры окна приложения
		float aspect  = 3f / 4f;
		config.height = 500;
		config.width  = (int)( config.height * aspect );

		// запрещаем изменять размер экрана игры
		config.resizable = false;

		new LwjglApplication( new MyGdxGame(), config );
	}
}
