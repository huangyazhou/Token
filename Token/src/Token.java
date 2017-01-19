import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Token
{
    
    public static void main(String[] args)
    {
        
        if (!(createFile(new File(System.getProperty("user.dir") + "\\bbb.txt"))))
            return;
        
        String str = readTxtFile(new File(System.getProperty("user.dir") + "\\aaa.txt"));
        
        String authToken = SHA1.toSHA1(str);
        
        contentToTxt(System.getProperty("user.dir") + "\\bbb.txt", authToken);
    }
    
    public static String readTxtFile(File fileName)
    {
        
        String result = "";
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try
        {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            try
            {
                String read = "";
                while ((read = bufferedReader.readLine()) != null)
                {
                    result = result + read + "\r\n";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fileReader != null)
            {
                try
                {
                    fileReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("readTxtFile result: " + result.trim());
        return result.trim();
    }
    
    public static void contentToTxt(String filePath, String content)
    {
        String str = new String();
        String s1 = new String();
        try
        {
            File f = new File(filePath);
            if (!(f.exists()))
            {
                f.createNewFile();
            }
            BufferedReader input = new BufferedReader(new FileReader(f));
            
            while ((str = input.readLine()) != null)
            {
                s1 = s1 + str + "\n";
            }
            System.out.println(s1);
            input.close();
            s1 = s1 + content;
            
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean createFile(File fileName)
    {
        boolean flag = false;
        try
        {
            if (!(fileName.exists()))
            {
                fileName.createNewFile();
                flag = true;
                return flag;
            }
            
            fileName.delete();
            fileName.createNewFile();
            flag = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
}
