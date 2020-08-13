package com.boz;

public class Main {
	public static void main(String[] args) {
		PdfToWord test = new PdfToWord();
		String res = new PdfToWord().pdftoword("E:\\培训\\java\\IdGenerator使用示例.pdf");
		System.out.println(res);
	}
}
