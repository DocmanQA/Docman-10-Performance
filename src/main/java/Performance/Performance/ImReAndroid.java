package Performance.Performance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.*;
import org.opencv.imgproc.Imgproc;

public class ImReAndroid {
	public static Mat img; 
	public static Mat temp; 
	public static Mat result;
	
	public static void main( String[] args )
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		//System.out.println("mat = " + mat.dump());
		//System.load("C:\\OpenCV-3.1\\opencv\\build\\java\\opencv-310.jar");
		//System.load("C:\\OpenCV-3.1\\opencv\\build\\java\\x64\\opencv_java310.dll");
		SimpleDateFormat TimeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date BeforeTime = Calendar.getInstance().getTime();
		System.out.println(BeforeTime.getTime());
        Mat model = Highgui.imread("C:/OpenCV-3.1/Temp.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat scene = Highgui.imread("C:/OpenCV-3.1/Source.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat diff = new Mat();
        Core.absdiff(model,scene,diff);
        Imgproc.threshold(diff,diff,15,255,Imgproc.THRESH_BINARY);
        int distortion = Core.countNonZero(diff);
        Date AfterTime = Calendar.getInstance().getTime();
        System.out.println(AfterTime.getTime());
        System.out.println("distortion:"+distortion);
        Highgui.imwrite("C:\\OpenCV-3.1\\diff.jpg",diff);
        System.out.println("The duration is " + (AfterTime.getTime() - BeforeTime.getTime()));
        
	}
	


}
