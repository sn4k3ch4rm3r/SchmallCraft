package schmallcraft.game.rendering;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PixelLighShader {
	private static ExecutorService executorService = Executors.newFixedThreadPool(8);

	private static float[][] palette = {
			{ 23f, 32f, 56f },
			{ 37f, 58f, 94f },
			{ 60f, 94f, 139f },
			{ 79f, 143f, 186f },
			{ 115f, 190f, 211f },
			{ 164f, 221f, 219f },
			{ 25f, 51f, 45f },
			{ 37f, 86f, 46f },
			{ 70f, 130f, 50f },
			{ 117f, 167f, 67f },
			{ 168f, 202f, 88f },
			{ 208f, 218f, 145f },
			{ 77f, 43f, 50f },
			{ 122f, 72f, 65f },
			{ 173f, 119f, 87f },
			{ 192f, 148f, 115f },
			{ 215f, 181f, 148f },
			{ 231f, 213f, 179f },
			{ 52f, 28f, 39f },
			{ 96f, 44f, 44f },
			{ 136f, 75f, 43f },
			{ 190f, 119f, 43f },
			{ 222f, 158f, 65f },
			{ 232f, 193f, 112f },
			{ 36f, 21f, 39f },
			{ 65f, 29f, 49f },
			{ 117f, 36f, 56f },
			{ 165f, 48f, 48f },
			{ 207f, 87f, 60f },
			{ 218f, 134f, 62f },
			{ 30f, 29f, 57f },
			{ 64f, 39f, 81f },
			{ 122f, 54f, 123f },
			{ 162f, 62f, 140f },
			{ 198f, 81f, 151f },
			{ 223f, 132f, 165f },
			{ 9f, 10f, 20f },
			{ 16f, 20f, 31f },
			{ 21f, 29f, 40f },
			{ 32f, 46f, 55f },
			{ 57f, 74f, 80f },
			{ 87f, 114f, 119f },
			{ 129f, 151f, 150f },
			{ 168f, 181f, 178f },
			{ 199f, 207f, 204f },
			{ 235f, 237f, 233f }
	};
	// 2x2 dithering matrix
	private static int[][] M = {
			{ 0, 2 },
			{ 3, 1 }
	};
	private static int n = M[0].length;
	private static double s = 16 / Math.pow(n, 2);

	private static double colorDistance(float[] c1RGB, float[] c2RGB) {
		float distance = 0;
		for (int i = 0; i < 3; i++) {
			distance += (c1RGB[i] - c2RGB[i]) * (c1RGB[i] - c2RGB[i]);
		}
		return Math.sqrt(distance);
	}

	private static float[] ditherColor(float[] cRGB, int x, int y) {
		float[] c2RGB = new float[3];
		for (int i = 0; i < 3; i++) {
			c2RGB[i] = (float) (cRGB[i] + s * (M[x % n][y % n]));
		}

		return getNearestColor(c2RGB);
	}

	private static float[] getNearestColor(float[] c) {
		float[] nearestColor = palette[0];
		double minDistance = colorDistance(nearestColor, c);
		for (int i = 1; i < palette.length; i++) {
			if (colorDistance(palette[i], c) < minDistance) {
				nearestColor = palette[i];
				minDistance = colorDistance(nearestColor, c);
			}
		}
		return nearestColor;
	}

	public static void renderLights(BufferedImage baseScreenBuffer, List<Lightsource> lightSources, Camera camera)
			throws InterruptedException, ExecutionException {
		List<Future<WritableRaster>> futures = new ArrayList<>();
		for (Lightsource lightsource : lightSources) {
			LightRenderer task = new LightRenderer(lightsource,
					camera.worldToRenderCoords(lightsource.getLightPosition()), baseScreenBuffer);
			futures.add(executorService.submit(task));
		}

		WritableRaster raster = baseScreenBuffer.getRaster();
		WritableRaster lightBuffer = futures.get(0).get();

		for (int i = 1; i < futures.size(); i++) {
			WritableRaster lightBuffer2 = futures.get(i).get();
			for (int x = 0; x < baseScreenBuffer.getWidth(); x++) {
				for (int y = 0; y < baseScreenBuffer.getHeight(); y++) {
					float[] pixel = lightBuffer.getPixel(x, y, (float[]) null);
					float[] pixel2 = lightBuffer2.getPixel(x, y, (float[]) null);
					for (int j = 0; j < 3; j++) {
						pixel[j] += pixel2[j];
						pixel[j] = Math.min(pixel[j], 255);
					}
					lightBuffer.setPixel(x, y, pixel);
				}
			}
		}
		raster.setRect(lightBuffer);
		for (int x = 0; x < raster.getWidth(); x++) {
			for (int y = 0; y < raster.getHeight(); y++) {
				raster.setPixel(x, y,
						ditherColor(raster.getPixel(x, y, (float[]) null), x, y));
			}
		}
	}
}
