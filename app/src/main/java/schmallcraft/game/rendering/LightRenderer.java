package schmallcraft.game.rendering;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.concurrent.Callable;

import schmallcraft.util.Vector2;

/**
 * A világban lévő fényeket rendereli külön szálon.
 */
public class LightRenderer implements Callable<WritableRaster> {
	private Lightsource lightsource;
	private Vector2 lightRenderPosition;
	private WritableRaster lightBuffer;
	private BufferedImage baseScreenBuffer;

	public LightRenderer(Lightsource lightsource, Vector2 lightRenderPosition, BufferedImage baseScreenBuffer) {
		this.lightsource = lightsource;
		this.lightRenderPosition = lightRenderPosition;
		this.baseScreenBuffer = baseScreenBuffer;
	}

	@Override
	public WritableRaster call() {
		lightBuffer = baseScreenBuffer.copyData(null);
		WritableRaster raster = baseScreenBuffer.getRaster();

		for (int x = 0; x < baseScreenBuffer.getWidth(); x++) {
			for (int y = 0; y < baseScreenBuffer.getHeight(); y++) {
				double distance = Math.sqrt(Math.pow(lightRenderPosition.x - x, 2) +
						Math.pow(lightRenderPosition.y - y, 2));
				double[] pixel = new double[3];
				if (distance < lightsource.getLightLevel()) {
					for (int i = 0; i < 3; i++) {
						pixel[i] = (Math.pow((lightsource.getLightLevel() - distance) / lightsource.getLightLevel(), 2))
								* lightsource.getLightColor().getRGBColorComponents(null)[i]
								* raster.getPixel(x, y, (double[]) null)[i];
					}
				}
				lightBuffer.setPixel(x, y, pixel);
			}
		}
		return lightBuffer;
	}
}
