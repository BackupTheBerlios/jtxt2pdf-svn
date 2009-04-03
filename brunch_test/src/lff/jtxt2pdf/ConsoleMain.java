package lff.jtxt2pdf;

import java.io.*;

import lff.jtxt2pdf.logger.ILogger;
import lff.jtxt2pdf.logger.Logger;
import lff.jtxt2pdf.utility.I18NUtility;
import lff.jtxt2pdf.utility.FileUtility;
import com.lowagie.text.*;

public class ConsoleMain {
	
	private Option option = new Option();
	
	public ConsoleMain() {
		
	}
	
	public static void main(String[] argu) throws DocumentException, IOException {
		ConsoleMain main = new ConsoleMain();
		Logger.setLogger(new ILogger() {
			public void info(String msg) {
				System.out.println(msg);
			}
			public void debug(String msg) {
				System.out.println(msg);
			}
			public void error(String msg) {
				System.out.println(msg);
			}
		});
		Logger.logInfo(I18NUtility.getMessage("main.license") + " " + Version.getVersion());
		main.initArguments(argu);
		if (main.checked()) {
			Processor.process(main.option);
		} 
	}


	private boolean checked() {
		if (option.source == null) {
			Logger.logInfo("Source file is required!");
			System.exit(1);
		}
		File f = new File(option.source);
		if (!f.exists()) {
			Logger.logInfo("Source file does not exist!");
			System.exit(1);
		}
		if (!f.isFile()) {
			Logger.logInfo("Source file is not valid!");
			System.exit(1);
		}
		if (option.target == null) {
			String s = "Target file is not set. Will be set to ";
			option.target = FileUtility.converTail(option.source);
			Logger.logInfo(s + option.target);
		}
		
		File t = new File(option.target);
		if (t.exists() && !option.overwrite) {
			Logger.logInfo("Target file " + t.getAbsolutePath() + " exists. You must set -overwrite, or set another name.");
			System.exit(1);
		}
		return true;
	}

	public void initArguments(String[] argu) {
		if (argu == null || argu.length == 0) {
			outputHelp();
			System.exit(1);
		}
		
		int i=0;
		String key = "";
		String value = "";
		try {
			while (i < argu.length) {
				key = argu[i];
				if (key.equalsIgnoreCase("-fontpath")) {
					i++;
					value = argu[i];
					option.fontFolder = value;
					i++;
					continue;
				}
				if (key.equalsIgnoreCase("-fontname")) {
						i++;
						value = argu[i];
						option.fontName = value;
						i++;
						continue;
				}
				
				if (key.equalsIgnoreCase("-size")) {
					i++;
					value = argu[i];
					try{
						option.size = Integer.parseInt(value);
					} catch (Exception e) {
						Logger.logError("Error: -size must be followed by a digit! " + value + " is not a valid dight. ");
						System.exit(2);
					}
					i++;
					continue;
				}			
				
				if (key.equalsIgnoreCase("-bold")) {
					i++;
					value = argu[i];
					if (value != null) {
						option.isBold = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1") ||
										value.equalsIgnoreCase("bold");					
					}
	
					i++;
					continue;
				}
				if (key.equalsIgnoreCase("-cfg")) {
					i++;
					value = argu[i];
					readInConfig(value);
					i++;
					continue;
				}			
				if (key.equalsIgnoreCase("-s")) {
					i++;
					value = argu[i];
					option.source = value;
					i++;
					continue;
				}				
				if (key.equalsIgnoreCase("-t")) {
					i++;
					value = argu[i];
					option.target = value;
					i++;
					continue;
				}				
				if (key.equalsIgnoreCase("-overwrite")) {
					i++;
					option.overwrite = true;
					continue;
				}		
				if (key.equalsIgnoreCase("-encode")) {
					i++;
					value = argu[i];
					option.encode = value;
					i++;
					continue;
				}
				if (key.equalsIgnoreCase("-compress")) {
					i++;
					value = argu[i];
					if (value != null) {
						if (value.equalsIgnoreCase("0") || value.equalsIgnoreCase("false")) {
							option.compress = false;
						}
					}
					i++;
					continue;
				}				
				Logger.logError("Invalid option " + key + " found. Exit.");
				System.exit(1);
			} //end while
		} catch (Exception e) {
			Logger.logError("Reading option failed. Please check."); 
			System.exit(1);
		}
	}
	private void readInConfig(String value) {
		
	}

	private void outputHelp() {
		Logger.logInfo(I18NUtility.getMessage("main.usage"));
	}
	
}

