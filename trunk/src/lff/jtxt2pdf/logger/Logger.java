package lff.jtxt2pdf.logger;

public class Logger {
	private static ILogger logger;
	private Logger(ILogger logger) {
		throw new IllegalArgumentException("Do not call this method.");
	}
	
	public static void setLogger(ILogger l) {
		if (l == null) {
			throw new NullPointerException("Logger is null!");
		}
		logger = l;
	}
	
	public static void logDebug(String msg) {
		if (logger == null) {
			throw new NullPointerException("Logger is null!");
		}
		logger.debug(msg);
	}
	public static void logInfo(String msg) {
		if (logger == null) {
			throw new NullPointerException("Logger is null!");
		}
		logger.info(msg);
	}
	
	public static void logError(String msg) {
		if (logger == null) {
			throw new NullPointerException("Logger is null!");
		}
		logger.error(msg);
	}
	
}
