package com.test.core.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;



/**
 * 封装了些文件相关的操作
 */
public final class FileUtil {
	private static String PATH;
	static {
		String str0 = new FileUtil().getClass().getClassLoader()
				.getResource("/").getPath();
		String str1 = StringUtils.substringAfter(str0, "/");
		String str2 = StringUtils.substringBefore(str1, "/WEB-INF");
		PATH = str2;
	}
	
	
	 /**
     * 判断是否符是合法的文件路径
     *
     * @param path 需要处理的文件路径
     */
    public final static boolean legalFile(String path) {
        //下面的正则表达式有问题
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        //String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegUtil.isMatche(commandPath(path), regex);
    }

    /**
     * 返回一个通用的文件路径
     *
     * @param file 需要处理的文件路径
     * @return
     * Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public final static String commandPath(String file) {
        return file.replaceAll("\\\\{1,}", "/").replaceAll("\\/{2,}", "/");
    }

 
	/**
	 * 获取web 应用的名字
	 * 
	 * @return web 应用名字 mettingsys
	 */
	public static String getWebName() {
		return StringUtils.substringAfterLast(PATH, "/");
	}
 
	/**
	 * 获取web 应用所在服务器磁盘的目录
	 * 
	 * @return D:/Software/Java/Server/tomcat7.04/webapps
	 */
	public static String getWebLocDir() {
		return StringUtils.substringBeforeLast(PATH, "/");
	}
 
	/**
	 * 获取web 应用的物理路径
	 * 
	 * @return 字符串：D:/Software/Java/Server/tomcat7.04/webapps/mettingsys
	 */
	public static String getWebLocPath() {
		/**
		 * 	String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
			path=path.replace('/', '\\'); // 将/换成\
			path=path.replace("file:", ""); //去掉file:
			path=path.replace("WEB-INF\\classes\\", ""); //去掉WEB-INF\class\
			path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
		 */
		return PATH;
	}
	
 

    public static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    /**
     * 获取文件的md5
     * @param file
     * @return
     */
    public static String fileMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new BigInteger(1, MD5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
   
   

   

    /**
     * 获取文件的Mime类型
     *
     * @param file 需要处理的文件
     * @return 返回文件的mime类型
     * @throws java.io.IOException
     */
    public final static String mimeType(String file) throws java.io.IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file);
    }

   

    /**
     * 获取文件最后的修改时间
     *
     * @param file 需要处理的文件
     * @return 返回文件的修改时间
     */
    public final static Date modifyTime(File file) {
        return new Date(file.lastModified());
    }




    

   



   
    /**
     * 创建多级目录
     *
     * @param paths 需要创建的目录
     * @return 是否成功
     */
    public final static boolean createPaths(String paths) {
        File dir = new File(paths);
        return !dir.exists() && dir.mkdir();
    }

    /**
     * 创建文件支持多级目录
     *
     * @param filePath 需要创建的文件
     * @return 是否成功
     */
    public final static boolean createFiles(String filePath) {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 删除一个文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 删除一个目录
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean deleteDir(File file) {
        List<File> files = listFileAll(file);
        if (CheckUtil.valid(files)) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDir(f);
                } else {
                    deleteFile(f);
                }
            }
        }
        return file.delete();
    }



  
    
    /**
     * 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @return 包含所有文件的的list
     */
    public final static List<File> listFile(String path) {
        File file = new File(path);
        return listFile(file);
    }

    /**
     * 罗列指定路径下的全部文件
     * @param path 需要处理的文件
     * @param child 是否罗列子文件
     * @return 包含所有文件的的list
     */
    public final static List<File> listFile(String path,boolean child){
        return listFile(new File(path),child);
    }


    /**
     * 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     */
    public final static List<File> listFile(File path) {
        List<File> list = new ArrayList<File>();
        File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFile(file));
                } else {
                    list.add(file);
                }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件
     * @param path 指定的路径
     * @param child 是否罗列子目录
     * @return
     */
    public final static List<File> listFile(File path,boolean child){
        List<File> list = new ArrayList<File>();
        File[] files = path.listFiles();
            for (File file : files) {
                if (child && file.isDirectory()) {
                    list.addAll(listFile(file));
                } else {
                    list.add(file);
                }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件夹
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     */
    public final static List<File> listFileAll(File path) {
        List<File> list = new ArrayList<File>();
        File[] files = path.listFiles();
            for (File file : files) {
                list.add(file);
                if (file.isDirectory()) {
                    list.addAll(listFileAll(file));
                }
            }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件夹
     *
     * @param path   需要处理的文件
     * @param filter 处理文件的filter
     * @return 返回文件列表
     */
    public final static List<File> listFileFilter(File path, FilenameFilter filter) {
        List<File> list = new ArrayList<File>();
        File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, filter));
                } else {
                    if (filter.accept(file.getParentFile(), file.getName())) {
                        list.add(file);
                    }
                }
        }
        return list;
    }

    /**
     * 获取指定目录下的特点文件,通过后缀名过滤
     *
     * @param dirPath  需要处理的文件
     * @param postfixs 文件后缀
     * @return 返回文件列表
     */
    public final static List<File> listFileFilter(File dirPath, final String postfixs) {
        /*
        如果在当前目录中使用Filter讲只罗列当前目录下的文件不会罗列孙子目录下的文件
        FilenameFilter filefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(postfixs);
            }
        };
        */
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, postfixs));
                } else {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(postfixs.toLowerCase())) {
                        list.add(file);
                    }
                }
        }
        return list;
    }

    /**
     * 在指定的目录下搜寻文个文件
     *
     * @param dirPath  搜索的目录
     * @param fileName 搜索的文件名
     * @return 返回文件列表
     */
    public final static List<File> searchFile(File dirPath, String fileName) {
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, fileName));
                } else {
                    String Name = file.getName();
                    if (Name.equals(fileName)) {
                        list.add(file);
                    }
                }
        }
        return list;
    }

    /**
     * 查找符合正则表达式reg的的文件
     *
     * @param dirPath 搜索的目录
     * @param reg     正则表达式
     * @return 返回文件列表
     */
    public final static List<File> searchFileReg(File dirPath, String reg) {
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, reg));
                } else {
                    String Name = file.getName();
                    if (RegUtil.isMatche(Name, reg)) {
                        list.add(file);
                    }
                }
            }
        return list;
    }


    /**
     * 获取文件后缀名
     * @param file
     * @return
     */
    public final static String suffix(File file){
        String fileName=file.getName();
        return fileName.substring(fileName.indexOf(".")+1);
    }
    
    /** 
     * 获取文件的后缀名并转化成大写 
     *  
     * @param fileName 
     *            文件名 
     * @return 
     */  
    public String getFileSuffix(String fileName) throws Exception {  
        return fileName.substring(fileName.lastIndexOf(".") + 1,  
                fileName.length()).toUpperCase();  
    }  
  
    /** 
     * 创建多级目录 
     *  
     * @param path 
     *            目录的绝对路径 
     */  
    public void createMultilevelDir(String path) {  
        try {  
            StringTokenizer st = new StringTokenizer(path, "/");  
            String path1 = st.nextToken() + "/";  
            String path2 = path1;  
            while (st.hasMoreTokens()) {  
  
                path1 = st.nextToken() + "/";  
                path2 += path1;  
                File inbox = new File(path2);  
                if (!inbox.exists())  
                    inbox.mkdir();  
  
            }  
        } catch (Exception e) {  
            System.out.println("目录创建失败" + e);  
            e.printStackTrace();  
        }  
  
    }  
  
    /** 
     * 删除文件/目录(递归删除文件/目录) 
     *  
     * @param path 
     *            文件或文件夹的绝对路径 
     */  
    public void deleteAll(String dirpath) {  
        if (dirpath == null) {  
            System.out.println("目录为空");  
        } else {  
            File path = new File(dirpath);  
            try {  
                if (!path.exists())  
                    return;// 目录不存在退出  
                if (path.isFile()) // 如果是文件删除  
                {  
                    path.delete();  
                    return;  
                }  
                File[] files = path.listFiles();// 如果目录中有文件递归删除文件  
                for (int i = 0; i < files.length; i++) {  
                    deleteAll(files[i].getAbsolutePath());  
                }  
                path.delete();  
  
            } catch (Exception e) {  
                System.out.println("文件/目录 删除失败" + e);  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 文件/目录 重命名 
     *  
     * @param oldPath 
     *            原有路径（绝对路径） 
     * @param newPath 
     *            更新路径 
     * @author lyf 注：不能修改上层次的目录 
     */  
    public void renameDir(String oldPath, String newPath) {  
        File oldFile = new File(oldPath);// 文件或目录  
        File newFile = new File(newPath);// 文件或目录  
        try {  
            boolean success = oldFile.renameTo(newFile);// 重命名  
            if (!success) {  
                System.out.println("重命名失败");  
            } else {  
                System.out.println("重命名成功");  
            }  
        } catch (RuntimeException e) {  
            e.printStackTrace();  
        }  
  
    }  
    
 
    
  
    
    
    
    
  
  
    
    private static final String FOLDER_SEPARATOR = "/";  
    private static final char EXTENSION_SEPARATOR = '.';  
  
    /** 
     * 功能：复制文件或者文件夹。 
     *  
     *  
     * @date 2014年06月24日 
     * @param inputFile 
     *            源文件 
     * @param outputFile 
     *            目的文件 
     * @param isOverWrite 
     *            是否覆盖(只针对文件) 
     * @throws IOException 
     */  
    public static void copy(File inputFile, File outputFile, boolean isOverWrite)  
            throws IOException {  
        if (!inputFile.exists()) {  
            throw new RuntimeException(inputFile.getPath() + "源目录不存在!");  
        }  
        copyPri(inputFile, outputFile, isOverWrite);  
    }  
  
    /** 
     * 功能：为copy 做递归使用。 
     *  
     *  
     * @date 2014年06月24日 
     * @param inputFile 
     * @param outputFile 
     * @param isOverWrite 
     * @throws IOException 
     */  
    private static void copyPri(File inputFile, File outputFile,  
            boolean isOverWrite) throws IOException {  
        // 是个文件。  
        if (inputFile.isFile()) {  
            copySimpleFile(inputFile, outputFile, isOverWrite);  
        } else {  
            // 文件夹  
            if (!outputFile.exists()) {  
                outputFile.mkdir();  
            }  
            // 循环子文件夹  
            for (File child : inputFile.listFiles()) {  
                copy(child,  
                        new File(outputFile.getPath() + "/" + child.getName()),  
                        isOverWrite);  
            }  
        }  
    }  
  
    /** 
     * 功能：copy单个文件 
     *  
     *  
     * @date 2014年06月24日 
     * @param inputFile 
     *            源文件 
     * @param outputFile 
     *            目标文件 
     * @param isOverWrite 
     *            是否允许覆盖 
     * @throws IOException 
     */  
    private static void copySimpleFile(File inputFile, File outputFile,  
            boolean isOverWrite) throws IOException {  
        // 目标文件已经存在  
        if (outputFile.exists()) {  
            if (isOverWrite) {  
                if (!outputFile.delete()) {  
                    throw new RuntimeException(outputFile.getPath() + "无法覆盖！");  
                }  
            } else {  
                // 不允许覆盖  
                return;  
            }  
        }  
        InputStream in = new FileInputStream(inputFile);  
        OutputStream out = new FileOutputStream(outputFile);  
        byte[] buffer = new byte[1024];  
        int read = 0;  
        while ((read = in.read(buffer)) != -1) {  
            out.write(buffer, 0, read);  
        }  
        in.close();  
        out.close();  
    }  
  
    /** 
     * 功能：删除文件 
     *  
     *  
     * @date 2014年06月24日 
     * @param file 
     *            文件 
     */  
    public static void delete(File file) {  
        deleteFile(file);  
    }  
  
    /** 
     * 功能：删除文件，内部递归使用 
     *  
     *  
     * @date 2014年06月24日 
     * @param file 
     *            文件 
     * @return boolean true 删除成功，false 删除失败。 
     */  
    public static void deleteFile2(File file) {  
        if (file == null || !file.exists()) {  
            return;  
        }  
        // 单文件  
        if (!file.isDirectory()) {  
            boolean delFlag = file.delete();  
            if (!delFlag) {  
                throw new RuntimeException(file.getPath() + "删除失败！");  
            } else {  
                return;  
            }  
        }  
        // 删除子目录  
        for (File child : file.listFiles()) {  
            deleteFile(child);  
        }  
        // 删除自己  
        file.delete();  
    }  
  
    /** 
     * 从文件路径中抽取文件的扩展名, 例如. "mypath/myfile.txt" -> "txt". *  
     *  
     * @date 2014年06月24日 
     * @param 文件路径 
     * @return 如果path为null，直接返回null。 
     */  
    public static String getFilenameExtension(String path) {  
        if (path == null) {  
            return null;  
        }  
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);  
        if (extIndex == -1) {  
            return null;  
        }  
        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);  
        if (folderIndex > extIndex) {  
            return null;  
        }  
        return path.substring(extIndex + 1);  
    }  
  
    /** 
     * 从文件路径中抽取文件名, 例如： "mypath/myfile.txt" -> "myfile.txt"。 *  
     *  
     * @date 2014年06月24日 
     * @param path 
     *            文件路径。 
     * @return 抽取出来的文件名, 如果path为null，直接返回null。 
     */  
    public static String getFilename(String path) {  
        if (path == null) {  
            return null;  
        }  
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);  
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1)  
                : path);  
    }  
  
    /** 
     * 功能：保存文件。 
     *  
     *  
     * @date 2014年06月24日 
     * @param content 
     *            字节 
     * @param file 
     *            保存到的文件 
     * @throws IOException 
     */  
    public static void save(byte[] content, File file) throws IOException {  
        if (file == null) {  
            throw new RuntimeException("保存文件不能为空");  
        }  
        if (content == null) {  
            throw new RuntimeException("文件流不能为空");  
        }  
        InputStream is = new ByteArrayInputStream(content);  
        save(is, file);  
    }  
  
    /** 
     * 功能：保存文件 
     *  
     *  
     * @date 2014年06月24日 
     * @param streamIn 
     *            文件流 
     * @param file 
     *            保存到的文件 
     * @throws IOException 
     */  
    public static void save(InputStream streamIn, File file) throws IOException {  
        if (file == null) {  
            throw new RuntimeException("保存文件不能为空");  
        }  
        if (streamIn == null) {  
            throw new RuntimeException("文件流不能为空");  
        }  
        // 输出流  
        OutputStream streamOut = null;  
        // 文件夹不存在就创建。  
        if (!file.getParentFile().exists()) {  
            file.getParentFile().mkdirs();  
        }  
        streamOut = new FileOutputStream(file);  
        int bytesRead = 0;  
        byte[] buffer = new byte[8192];  
        while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {  
            streamOut.write(buffer, 0, bytesRead);  
        }  
        streamOut.close();  
        streamIn.close();  
    }  
}