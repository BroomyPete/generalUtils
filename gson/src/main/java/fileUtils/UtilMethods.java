package fileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class UtilMethods {

	public static void deleteFilesOlderThanNdays(Path workingDir, int daysBack) {

		int dayInMilis = 24 * 60 * 60 * 1000;

		long purgeTime = System.currentTimeMillis() - (daysBack * dayInMilis);

		try {
			Files.list(workingDir).filter(p -> p.toFile().lastModified() < purgeTime).forEach(p -> {
				try {
					Files.deleteIfExists(p);
				} catch (IOException e) {
					System.err.println("Unable to delte file:- " + p.getFileName());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Path myPath = Paths.get("C:\\temp");
		deleteFilesOlderThanNdays(myPath, 3);
	}
}
