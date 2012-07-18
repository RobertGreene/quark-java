package com.friendster.client.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class ReadAsset {

	public static String getAssetChecksum(String fileName)
			throws NoSuchAlgorithmException, IOException {
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);
		byte[] reader = new byte[1];
		List<Byte> readBytes = new ArrayList<Byte>();
		while (fis.read(reader) != -1) {
			readBytes.add(reader[0]);
		}
		int fileLength = readBytes.size();
		byte[] readFile = new byte[fileLength];
		for (int i = 0; i < fileLength; i++) {
			readFile[i] = readBytes.get(i);
		}
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		return byteArray2Hex(md.digest(readFile));
	}

	private static String byteArray2Hex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	public static void main(String[] args) {
		try {
			String checkSum = getAssetChecksum("/home/dev/Downloads/MasteringSpringMVC3.pdf");
			System.out.println("Hex Value     : " + checkSum);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}