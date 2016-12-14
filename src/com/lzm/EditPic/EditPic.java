package com.lzm.EditPic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditPic {

	private Logger log = LoggerFactory.getLogger(getClass());

	// 用户名字典
	static String[] firsname = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", "朱",
			"秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水",
			"窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁",
			"柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
			"乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚",
			"邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪",
			"舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闽", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜",
			"郭", "梅", "盛", "林", "刁", "锺", "徐", "丘", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯",
			"昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包",
			"诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "麹",
			"家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山",
			"谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "斜", "厉", "戎",
			"祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "薄", "印", "宿", "白", "怀", "蒲",
			"邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘",
			"党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "郤", "璩", "桑", "桂", "濮", "牛", "寿",
			"通", "边", "扈", "燕", "冀", "郏", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习",
			"宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡",
			"国", "文", "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾",
			"敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯",
			"相", "查", "后", "荆", "红", "游", "竺", "权", "逑", "盖", "益", "桓", "公", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人",
			"东方", "赫连", "皇甫", "尉迟", "公羊", "澹台", "公冶", "宗政", "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "锺离",
			"宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "丌官", "司寇", "仉", "督", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "乐正",
			"壤驷", "公良", "拓拔", "夹谷", "宰父", "谷梁", "晋", "楚", "阎", "法", "汝", "鄢", "涂", "钦", "段干", "百里", "东郭", "南门", "呼延",
			"归", "海", "羊舌", "微生", "岳", "帅", "缑", "亢", "况", "后", "有", "琴", "梁丘", "左丘", "东门", "西门", "商", "牟", "佘", "佴",
			"伯", "赏", "南宫", "墨", "哈", "谯", "笪", "年", "爱", "阳", "佟" };
	static String[] namelist = { "伟", "伟", "芳", "伟", "秀英", "秀英", "娜", "秀英", "伟", "敏", "静", "丽", "静", "丽", "强", "静", "敏",
			"敏", "磊", "军", "洋", "勇", "勇", "艳", "杰", "磊", "强", "军", "杰", "娟", "艳", "涛", "涛", "明", "艳", "超", "勇", "娟",
			"杰", "秀兰", "霞", "敏", "军", "丽", "强", "平", "刚", "杰", "桂英", "芳", "　嘉懿", "煜城", "懿轩", "烨伟", "苑博", "伟泽", "熠彤",
			"鸿煊", "博涛", "烨霖", "烨华", "煜祺", "智宸", "正豪", "昊然", "明杰", "立诚", "立轩", "立辉", "峻熙", "弘文", "熠彤", "鸿煊", "烨霖", "哲瀚",
			"鑫鹏", "致远", "俊驰", "雨泽", "烨磊", "晟睿", "天佑", "文昊", "修洁", "黎昕", "远航", "旭尧", "鸿涛", "伟祺", "荣轩", "越泽", "浩宇", "瑾瑜",
			"皓轩", "擎苍", "擎宇", "志泽", "睿渊", "楷瑞", "子轩", "弘文", "哲瀚", "雨泽", "鑫磊", "修杰", "伟诚", "建辉", "晋鹏", "天磊", "绍辉", "泽洋",
			"明轩", "健柏", "鹏煊", "昊强", "伟宸", "博超", "君浩", "子骞", "明辉", "鹏涛", "炎彬", "鹤轩", "越彬", "风华", "靖琪", "明诚", "高格", "光华",
			"国源", "冠宇", "晗昱", "涵润", "翰飞", "翰海", "昊乾", "浩博", "和安", "弘博", "宏恺", "鸿朗", "华奥", "华灿", "嘉慕", "坚秉", "建明", "金鑫",
			"锦程", "瑾瑜", "晋鹏", "经赋", "景同", "靖琪", "君昊", "俊明", "季同", "开济", "凯安", "康成", "乐语", "力勤", "良哲", "理群", "茂彦", "敏博",
			"明达", "朋义", "彭泽", "鹏举", "濮存", "溥心", "璞瑜", "浦泽", "奇邃", "祺祥", "荣轩", "锐达", "睿慈", "绍祺", "圣杰", "晟睿", "思源", "斯年",
			"泰宁", "天佑", "同巍", "奕伟", "祺温", "文虹", "向笛", "心远", "欣德", "新翰", "兴言", "星阑", "修为", "旭尧", "炫明", "学真", "雪风", "雅昶",
			"阳曦", "烨熠", "英韶", "永贞", "咏德", "宇寰", "雨泽", "玉韵", "越彬", "蕴和", "哲彦", "振海", "正志", "子晋", "自怡", "德赫", "君平" };
	/*
	 * 初始化model变量
	 */
	public static BufferedImage model;
	static {
		try {
			model = ImageIO.read(new File("model/model.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("未找到图片");
		}
	}

	/*
	 * 背景图片，缩略图，字符串，矩形框放置在model图片上的位置 背景图片，缩略图的宽高
	 */
	private static int background_x = 15;
	private static int background_y = 0;
	private static int background_width = 760;
	private static int background_height = 245;
	private static int thumbs1_x = 605;
	private static int thumbs1_y = 156;
	private static int thumbs1_width = 130;
	private static int thumbs1_height = 130;
	private static int thumbs2_x = 24;
	private static int thumbs2_y = 365;
	private static int thumbs2_width = 88;
	private static int thumbs2_height = 88;
	private static int str1_x = 450;
	private static int str1_y = 200;
	private static int str2_x = 160;
	private static int str2_y = 390;
	private static int rectangle1_x = 15;
	private static int rectangle1_y = 355;
	private static int rectangle1_width = 108;
	private static int rectangle1_height = 108;
	private static int rectangle2_x = 595;
	private static int rectangle2_y = 145;
	private static int rectangle2_width = 148;
	private static int rectangle2_height = 148;

	private static String DEFAULT_THUMB_PREVFIX = "thumb_";
	private static String DEFAULT_BACKGROUND_PREVFIX = "background_";

	/**
	 * 传入icon或background目录名，例如："icon"或者"background" 注：该目录与src目录平级
	 * 读取该一级目录下所有图片文件名，使用ImageIO将图片文件读入到BufferedImage中 返回存储了BufferedImage的list集合
	 * 若该一级目录下存在二级或多级目录，需要使用递归(此处省略,只处理一级目录下图片文件)
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<BufferedImage> getPictures(String filePath) {
		try {
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			File file = new File(filePath);
			File[] pics = file.listFiles();
			for (int i = 0; i < pics.length; i++) {
				String fileName = pics[i].getName();
				BufferedImage image = ImageIO.read(new File(filePath + File.separator + fileName));
				images.add(image);
			}
			return images;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("未找到图片");
		}
		return null;

	}

	/**
	 * 传入icon或background目录名，例如："icon"或者"background" 注：该目录与src目录平级
	 * 返回存储了该目录下所有图片文件名的集合
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> getFileNames(String filePath) {
		List<String> files = new ArrayList<String>();
		File file = new File(filePath);
		File[] pics = file.listFiles();
		for (int i = 0; i < pics.length; i++) {
			String fileName = pics[i].getName();
			String name = filePath + File.separator + fileName;
			files.add(name);
		}
		return files;
	}

	/**
	 * 随机生成用户名
	 * 
	 * @return
	 */
	public static String generateUserName() {
		int a = (int) Math.abs(firsname.length * Math.random());
		int b = (int) Math.abs(namelist.length * Math.random());
		String name = firsname[a] + namelist[b];
		return name;
	}

	/**
	 * 缩率图 Title: thumbnailImage 测试发现压缩到了大部分图片2K Description: 根据图片路径生成缩略图
	 * 
	 * @param imagePath
	 *            原图片路径
	 * @param w
	 *            缩略图宽
	 * @param h
	 *            缩略图高
	 * @param prevfix
	 *            生成缩略图的前缀
	 * @param force
	 *            是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public void thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force) {
		if (imgFile.exists()) {
			try {
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png,
				// PNG,JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames());
				String suffix = null;
				// 获取图片后缀
				if (imgFile.getName().indexOf(".") > -1) {
					suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
				}
				// 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
					log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
					return;
				}
				log.debug("target image's size, width:{}, height:{}.");
				Image img = ImageIO.read(imgFile);
				if (!force) {
					// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
					int width = img.getWidth(null);
					int height = img.getHeight(null);
					if ((width * 1.0) / w < (height * 1.0) / h) {
						if (width > w) {
							h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
							log.debug("change image's height, width:{},height:{}.");
						}
					} else {
						if (height > h) {
							w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
							log.debug("change image's width, width:{},height:{}.");
						}
					}
				}
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
				g.dispose();
				
				File file = new File("new_"+imgFile.getParent());
				if(!file.exists()){
					file.mkdirs();
				}
				ImageIO.write(bi, suffix, new File(file.getName() + File.separator + prevfix + imgFile.getName()));
			} catch (IOException e) {
				log.error("generate thumbnail image failed.", e);
			}
		} else {
			log.warn("the image is not exist.");
		}
	}

	/**
	 * 编辑原图片，设置为指定宽高度，存储在新的目录下
	 * @param filePath
	 */
	public static void generateNewImg(String filePath) {
		List<String> fileNames = getFileNames(filePath);
		for (int i = 0; i < fileNames.size(); i++) {
			String fileName = fileNames.get(i);
			File imgFile = new File(fileName);
			if(filePath.equals("icon")){
				new EditPic().thumbnailImage(imgFile,thumbs1_width,thumbs1_height,1+DEFAULT_THUMB_PREVFIX,true);
				new EditPic().thumbnailImage(imgFile,thumbs2_width,thumbs2_height,2+DEFAULT_THUMB_PREVFIX,true);
			}else{
				new EditPic().thumbnailImage(imgFile,background_width,background_height,DEFAULT_BACKGROUND_PREVFIX,true);
			}
//			imgFile.delete();
		}
	}
	
	/**
	 * 传入指定目录名，删除目录下所有文件以及该目录
	 * @param path
	 */
	public static void deleteFile(String path){
		File fileDir = new File(path);
		if(fileDir.exists()){
			File[] files = fileDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
			fileDir.delete();
		}
	}
	
	public static void main(String[] args) throws IOException {

		generateNewImg("background");
		generateNewImg("icon");
		
		int wideth = model.getWidth();
		int height = model.getHeight();
		Graphics g = model.createGraphics();
		List<BufferedImage> backgrounds = getPictures("new_background");
		List<BufferedImage> icons = getPictures("new_icon");
		//设置画笔的颜色，画字符串，矩形边框时使用
		Color color1 = new Color(255,255,255);
		Color color2 = new Color(63,103,132);
		Color color3 = new Color(140,140,140);
		for (int i = 0; i < backgrounds.size(); i++) {
			
			BufferedImage background = backgrounds.get(i);
			BufferedImage icon1 = icons.get(i);
			BufferedImage icon2 = icons.get(i+icons.size()/2);
			g.drawImage(model, 0, 0, wideth, height, null);
			g.drawImage(background, background_x, background_y, background_width, background_height, null);
			g.drawImage(icon1, thumbs1_x, thumbs1_y, thumbs1_width, thumbs1_height, null);
			g.drawImage(icon2, thumbs2_x, thumbs2_y, thumbs2_width, thumbs2_height, null);
			
			String userName = generateUserName();
			g.setFont(new Font("黑体", Font.PLAIN, 30));
			g.setColor(color1);
			g.drawString(userName, str1_x, str1_y);
			//此处画出一个白色背景的矩形，覆盖掉上次循环在此处画出的字符串，然后在该矩形上画出字符串
			g.setColor(Color.WHITE);
			g.fillRect (140, 350, 200, 100);
			g.setColor(color2);
			g.drawString(userName, str2_x, str2_y);
			//g是Graphics对象,强制转换后可以设置画笔的粗细
			Graphics2D g2 = (Graphics2D)g;  
			g2.setColor(color3);
			g2.setStroke(new BasicStroke(2.0f));
			g2.drawRect(rectangle1_x , rectangle1_y, rectangle1_width, rectangle1_height); 
			g2.drawRect(rectangle2_x , rectangle2_y, rectangle2_width, rectangle2_height); 
			OutputStream outImage = new FileOutputStream(i+"test.png");
			ImageIO.write(model, "png", outImage);
		}
		//删除目录
		deleteFile("new_icon");
		deleteFile("new_background");
		
	}
	
	
	
}
