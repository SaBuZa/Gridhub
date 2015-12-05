package util;

import java.awt.Color;

public class Helper {

	public static Color getAlphaColor(Color baseColor, int alpha) {
		return new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), alpha);
	}

	public static Color getAlphaColorPercentage(Color baseColor, double alphaRatio) {
		return new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), Math.round(alphaRatio * 255));
	}

	public static Color blendColor(Color a, Color b, float blendRatio) {
		if (blendRatio == 0) {
			return a;
		} else if (blendRatio == 1) {
			return b;
		} else if (blendRatio < 0 || blendRatio > 1) {
			throw new IllegalArgumentException("Invalid blend ratio: " + blendRatio);
		} else {
			return new Color((int) (a.getRed() * (1 - blendRatio) + b.getRed() * blendRatio),
					(int) (a.getGreen() * (1 - blendRatio) + b.getGreen() * blendRatio),
					(int) (a.getBlue() * (1 - blendRatio) + b.getBlue() * blendRatio),
					(int) (a.getAlpha() * (1 - blendRatio) + b.getAlpha() * blendRatio));
		}
	}

	public static float sineInterpolate(float t, boolean easeIn, boolean easeOut) {
		if (easeIn && easeOut) {
			return (float) Math.sin(Math.PI * t - Math.PI / 2.0f) / 2.0f + 0.5f;
		} else if (easeIn) {
			return (float) Math.sin(Math.PI * t - Math.PI / 2.0f) + 1;
		} else if (easeOut) {
			return (float) Math.sin(Math.PI / 2 * t);
		} else
			throw new IllegalArgumentException("Invalid easing values. Cannot be both false.");
	}

	public static float sineInterpolate(float t) {
		return sineInterpolate(t, true, true);
	}

	public static float sineInterpolate(float a, float b, float t, boolean easeIn, boolean easeOut) {
		return interpolate(a, b, sineInterpolate(t, easeIn, easeOut));
	}

	public static float sineInterpolate(float a, float b, float t) {
		return interpolate(a, b, sineInterpolate(t, true, true));
	}

	public static float interpolate(float a, float b, float t) {
		if (t == 0) {
			return a;
		} else if (t == 1) {
			return b;
		} else if (t < 0 || t > 1) {
			throw new IllegalArgumentException("Invalid t value: " + t);
		} else {
			return a * (1 - t) + b * t;
		}
	}
}
