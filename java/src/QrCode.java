import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
 
import javax.imageio.ImageIO;
 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 
// /**
//  * @author Crunchify.com
//  * Updated: 03/20/2016 - added code to narrow border size 
//  */
 
public class QrCode 
{
	// Tutorial: http://zxing.github.io/zxing/apidocs/index.html

	private static int _imageWidth=250;
	private static int _imageHeight=250;

	public void setImageWidth(int width)
	{
		_imageWidth=width;
	}
	public void setImageHeight(int height)
	{
		_imageHeight=height;
	}
	public int getImageWidth()
	{
		return _imageWidth;
	}
	public int getImageHeight()
	{
		return _imageHeight;
	}
  
	public static boolean getQrImageFile(String codeText, String filePath) 
	{
		boolean result=true;

		int size = _imageHeight;
		String fileType = "bmp";
		File myFile = new File(filePath);
		try {
			
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(codeText, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(_imageWidth, CrunchifyWidth,
					BufferedImage.TYPE_BYTE_BINARY);
			image.createGraphics();
 
			System.out.println(CrunchifyWidth);

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, _imageWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

  			int shift=(_imageWidth/2)-(size/2); //alignment on center
	
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i+shift, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, myFile);
		} 
		catch (WriterException e) 
		{
			e.printStackTrace();
			result=false;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			result=false;
		}
		return result;//System.out.println("\n\nYou have successfully created QR Code.");
	}

	public static BufferedImage getQrImage(String codeText, int imageWidth, int imageHeight) 
	{
		_imageWidth=imageWidth;
		_imageHeight=imageHeight;

		int size = _imageHeight;
		String fileType = "bmp";
		File myFile = new File("123");

		BufferedImage image=null;
		try 
		{
			
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(codeText, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			image = new BufferedImage(_imageWidth, CrunchifyWidth,
					BufferedImage.TYPE_BYTE_BINARY);
			image.createGraphics();
 
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, _imageWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

  			int shift=(_imageWidth/2)-(size/2); //alignment on center
	
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i+shift, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, myFile);
		}
		catch (WriterException e) 
		{
			e.printStackTrace();
			image=new BufferedImage(_imageWidth, 1, BufferedImage.TYPE_BYTE_BINARY);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			//result=false;
		}

		return image;
	}
}