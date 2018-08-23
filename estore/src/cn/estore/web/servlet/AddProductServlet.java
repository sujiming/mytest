package cn.estore.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.estore.domain.Product;
import cn.estore.service.ProductService;
import cn.estore.utils.PicUtils;
import cn.estore.utils.UploadUtils;

/**
 * 上传商品图片，完成商品信息数据库保存
 * 
 * 
 */
public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> parameterMap = new HashMap<String, String>();

		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(
					diskFileItemFactory);
			fileUpload.setFileSizeMax(1024 * 1024 * 5);
			fileUpload.setHeaderEncoding("utf-8");
			
			try {
				List<FileItem> list = fileUpload.parseRequest(request);
				
				for (FileItem fileItem : list) {
					
					if (fileItem.isFormField()) {
						
						String name = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						parameterMap.put(name, value);
					} else {
						
						String fileName = fileItem.getName();
						if (fileName == null || fileName.trim().equals("")) {
							
							throw new RuntimeException("添加商品，必须要上传图片！");
						}
						
						fileName = UploadUtils.subFileName(fileName);
						
						String contentType = fileItem.getContentType();
						boolean isValid = UploadUtils.checkImgType(contentType);
						if (!isValid) {
							
							throw new RuntimeException("上传图片格式不正确的！");
						}
						
						String uuidname = UploadUtils
								.generateUUIDName(fileName);

						
						String randomDir = UploadUtils
								.generateRandomDir(uuidname);
						
						File dir = new File(getServletContext().getRealPath(
								"/upload" + randomDir));
						dir.mkdirs();

						
						InputStream in = new BufferedInputStream(fileItem
								.getInputStream());
						OutputStream out = new BufferedOutputStream(
								new FileOutputStream(new File(dir, uuidname)));
						int b;
						while ((b = in.read()) != -1) {
							out.write(b);
						}
						in.close();
						out.close();

						fileItem.delete();

						PicUtils picUtils = new PicUtils(getServletContext()
								.getRealPath("/upload" + randomDir)
								+ "/" + uuidname);
						picUtils.resize(100, 100);

						parameterMap.put("img", "/upload" + randomDir + "/"
								+ uuidname);
					}
				}

				Product product = new Product();
				BeanUtils.populate(product, parameterMap);

				ProductService productService = new ProductService();
				productService.addProduct(product);

				response.sendRedirect("/index.jsp");

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		} else {
			throw new RuntimeException("添加商品，必须使用文件上传form 表单！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
