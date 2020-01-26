package s3.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class CreateFolder {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean bool = false;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter bucket name");
			String bucketName = scanner.next();
			System.out.println("Enter File name");
			String fileName = scanner.next();
			String pathName = ".\\src\\DownloadedFiles\\" + bucketName;
			File file = new File(pathName);
			if (!file.exists())
				bool = file.mkdir();
			if (bool)
				System.out.println("Folder created");
			else
				System.out.println("Bhaad me jao, folder create nahi hua");
			FileOutputStream fos = new FileOutputStream(pathName + "\\" + fileName);
			System.out.println("File created, check kar le");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
