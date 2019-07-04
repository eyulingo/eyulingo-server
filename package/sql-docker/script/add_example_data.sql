USE eyulingo_db;

INSERT INTO `eyulingo_db`.`Admins` (`admin_name`, `admin_password`) VALUES ('admin1', 'admin1password');
INSERT INTO `eyulingo_db`.`Admins` (`admin_name`, `admin_password`) VALUES ('admin2', 'admin2password');
INSERT INTO `eyulingo_db`.`Admins` (`admin_name`, `admin_password`) VALUES ('admin3', 'admin3password');

INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('顺丰速运');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('邮政EMS');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('圆通快递');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('申通快递');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('韵达快递');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('汇通快递');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('中通快递');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('宅急送');
INSERT INTO `eyulingo_db`.`Delivers` (`deliver_name`) VALUES ('自提');

INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('1', 'Apple Store 零售店', '5d1d683a6344590007151455', '上海市黄浦区南京东路300号', '400-666-8800', '10:00', '22:00', '顺丰速运', '乌绮玉', 'Wuqiyu123456', '上海市静安区中华新路479号', '13640698865', '5d1d5e47634459000715143b');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('2', 'Microsoft 零售店', '5d1d685a634459000715145a', '北京市朝阳区太阳宫中路12号', '010-84430670', '10:00', '22:00', '中通快递', '宇怡然', 'Yuyiran123456', '北京市西城区珠市口西大街113-2号', '15381882050', '5d1d5e5a634459000715143d');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('3', 'FamilyMart 全家便利店', '5d1d6868634459000715145c', '上海市徐汇区宜山路站3号口', '021-54894998', '00:00', '23:59', '自提', '罕问兰', 'Hanwenlan123456', '上海市徐汇区漕溪北路915号', '13636136463', '5d1d5e6d634459000715143f');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('4', 'Lawson 罗森便利店', '5d1d687e6344590007151463', '上海市徐汇区漕溪北路88号圣爱大厦1层', '021-60857694', '00:00', '23:59', '自提', '幸和暖', 'Xinghenuan123456', '上海市徐汇区枫林路180号', '15819206626', '5d1d5e7a6344590007151441');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('5', '7-ELEVEn 便利店', '5d1d688e6344590007151466', '江苏省无锡市梁溪区汉昌西街87号', '13306191838', '00:00', '23:59', '自提', '析津', 'Xijin123456', '江苏省无锡市滨湖区运河西路1596号', '13960026223', '5d1d5e866344590007151443');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('6', 'MONT BLANC', '5d1d689a6344590007151468', '江苏省无锡市梁溪区梁溪区中山路168号', '510-82731852', '09:30', '21:30', '宅急送', '迟幼枫', 'Chiyoufeng123456', '江苏省无锡市梁溪区中山路333号', '15526356625', '5d1d5e936344590007151445');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('7', '华为零售店', '5d1d68a6634459000715146a', '江苏省南京市秦淮区洪武路88号', '15051867700', '08:30', '23:00', '顺丰速运', '冰枫', 'Bingfeng123456', '江苏省南京市雨花台区应天大街619号', '13611731420', '5d1d5e9d6344590007151447');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('8', '京东专卖店', '5d1d68b3634459000715146c', '江苏省南京市雨花台区经济开发区玉兰路88号', '950618', '08:00', '22:00', '圆通快递', '行若云', 'Xingruoyun123456', '江苏省南京市玄武区太平北路122号', '15288003437', '5d1d5eab6344590007151449');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('9', '魅族专卖店', '5d1d68bf634459000715146e', '重庆市江北区观音桥朗晴广场LG层A5号', '023-86063134', '09:30', '20:30', '韵达快递', '游天菱', 'Youtianling123456', '重庆市渝北区黄泥磅紫福路69号', '15090654947', '5d1d5eb7634459000715144b');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('10', 'LUSH Fresh Handmade Cosmetics', '5d1d68cc6344590007151470', '香港特別行政區油尖旺區九龍灣德福廣場1期1樓', '27234282', '11:00', '22:00', '邮政EMS', '方语', 'Fangyu123456', '香港特別行政區黃大仙區新蒲崗大有街34號', '53542013', '5d1d5ec2634459000715144d');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('11', 'Walmart 沃尔玛', '5d1d68de6344590007151473', '广东省广州市越秀区淘金路6-8号', '020-83588082', '08:00', '22:30', '汇通快递', '尚晴', 'Shangqing123456', '广东省广州市越秀区中山二路92号', '13527334811', '5d1d5ece634459000715144f');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('12', 'Mayo Clinic 药店', '5d1d68f36344590007151475', '浙江省宁波市海曙区药行街93号', '574-27877080', '08:30', '21:30', '申通快递', '祈海亦', 'Qihaiyi123456', '浙江省宁波市海曙区望春街道丽园北路668号', '13547406817', '5d1d5edd6344590007151451');
INSERT INTO `eyulingo_db`.`Stores` (`store_id`, `store_name`, `cover_id`, `store_address`, `store_phone`, `start_time`, `end_time`, `deliver_method`, `dist_name`, `dist_password`, `dist_location`, `dist_phone`, `dist_image_id`) VALUES ('13', 'McDonald\'s 麦当劳快餐店', '5d1d69026344590007151477', '天津市河东区十一经路和六纬路交口津东大厦1层', '022-24011356', '00:00', '23:59', '自提', '祭一禾', 'Jiyihe123456', '天津市和平区西安道68号', '13976189733', '5d1d5ee86344590007151453');

INSERT INTO `eyulingo_db`.`Users` (`user_id`, `user_name`, `password`, `user_phone`, `cover_id`) VALUES ('1', '第灵韵', 'Dilingyun123456', '13976189733', '5d1d5cbe634459000715142f');
INSERT INTO `eyulingo_db`.`Users` (`user_id`, `user_name`, `password`, `user_phone`, `cover_id`) VALUES ('2', '真采萱', 'Zhencaixuan123456', '13677992319', '5d1d5d326344590007151431');
INSERT INTO `eyulingo_db`.`Users` (`user_id`, `user_name`, `password`, `user_phone`, `cover_id`) VALUES ('3', '谭凝', 'Tanning123456', '15741863013', '5d1d5d416344590007151433');
INSERT INTO `eyulingo_db`.`Users` (`user_id`, `user_name`, `password`, `user_phone`, `cover_id`) VALUES ('4', '随千山', 'Suiqianshan123456', '15588449523', '5d1d5d526344590007151435');
INSERT INTO `eyulingo_db`.`Users` (`user_id`, `user_name`, `password`, `user_phone`, `cover_id`) VALUES ('5', '颜清秋', 'Yanqingqiu123456', '13002685402', '5d1d5d5e6344590007151437');
INSERT INTO `eyulingo_db`.`Users` (`user_id`, `user_name`, `password`, `user_phone`, `cover_id`) VALUES ('6', '无竹', 'Wuzhu123456', '15719921442', '5d1d5d6b6344590007151439');


INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('1', 'iPhone 8 Plus', '1', '4999.00', '4988.00', '15123', '迄今为止最先进的 iPhone。', '5d1d6ab66344590007151479');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('2', 'iPad Pro (12.9-inch)', '1', '6999.00', '6599.00', '23944', '最先进的生产力。', '5d1d6ac1634459000715147b');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('3', 'MacBook Pro (2019)', '1', '12999.00', '11699.00', '2534', '出类拔萃的专业笔记本电脑。', '5d1d6acc634459000715147d');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('4', 'Apple Watch (Series 4)', '1', '2699.00', '2499.00', '16039', '看看 Apple Watch 如何让你充满活力、保持健康，并时刻与人紧密连系，是你健康生活不可或缺的终极装置。', '5d1d6ad9634459000715147f');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('5', 'Surface Pro 6', '2', '5999.00', '5499.00', '4732', 'Surface Pro 6 新款笔记本平板二合一。', '5d1d6b3b6344590007151481');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('6', 'Surface Book 2', '2', '7999.00', '7299.00', '4456', '全新微软 Surface Book 2 微软官方商城热卖。第8代英特尔处理器，可选NVIDIA独立显卡，17小时电池续航时间，性能巨匠微软Surface Book 2，四重模式任意变换。', '5d1d6b476344590007151483');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('7', 'Surface Studio 2', '2', '21999.00', '19999.00', '393', 'Surface Studio 是微软推出的运行 64 位 Windows 10 的一体机产品。', '5d1d6b506344590007151485');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('8', 'Nokia Lumia 1520', '2', '4999.00', '4499.00', '49324', '诺基亚Lumia 1520 是 Nokia 公司设计和生产的一款平板手机。', '5d1d6b596344590007151487');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('9', '芝士咖喱猪排饭', '3', '14.50', '14.50', '501', '芝士、咖喱和猪排，即食包装盒饭。', '5d1d6bba6344590007151489');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('10', '滑蛋厚切猪排饭', '3', '13.00', '13.00', '254', '滑蛋、厚切猪排，即食包装盒饭。', '5d1d6bd6634459000715148d');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('11', '爆浆猪排饭', '3', '16.50', '16.50', '321', '爆浆特大号猪排，即食包装盒饭。', '5d1d6be0634459000715148f');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('12', '可口可乐（罐装）', '3', '3.00', '3.00', '152678', '300 毫升罐装冷藏可口可乐。', '5d1d6bea6344590007151491');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('13', '奥尔良鸡排盖浇饭', '4', '17.50', '17.50', '234', '奥尔良鸡排和盖浇汤汁，即食包装盒饭。', '5d1d6c2b6344590007151493');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('14', '大碗麻辣香锅饭', '4', '14.00', '14.00', '531', '特大碗麻辣香锅，即食包装盒饭。', '5d1d6c356344590007151496');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('15', '沙茶牛肉双拼饭', '4', '15.50', '14.00', '153', '沙茶酱和牛肉的双拼搭配，即食包装盒饭。', '5d1d6c416344590007151499');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('16', '百事可乐（罐装）', '4', '3.00', '3.00', '95865', '300 毫升罐装冷藏百事可乐。', '5d1d6c51634459000715149b');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('17', '奶油虾仁意大利面', '5', '15.00', '15.00', '543', '奶油虾仁配意大利面，即食包装食品。', '5d1d6e80634459000715149d');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('18', '咖喱鸡排饭团', '5', '7.00', '7.00', '135', '咖喱鸡排包裹米饭，即食包装食品。', '5d1d6e8c634459000715149f');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('19', '超级海鲜披萨', '5', '18.00', '18.00', '421', '海鲜速冻披萨（1 片装）。', '5d1d6ea363445900071514a1');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('20', '海鲜烩炒饭', '5', '16.50', '16.50', '43', '海鲜烩饭，即食包装盒饭。', '5d1d6eb363445900071514a3');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('21', '麻婆豆腐烩饭', '5', '14.00', '14.00', '276', '麻婆豆腐烩饭，即食包装烩饭。', '5d1d6ebe63445900071514a5');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('22', '炭烤风味鸡排便当', '5', '15.00', '15.00', '542', '炭烤风味鸡排米饭，即食包装食品。', '5d1d6ecb63445900071514a7');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('23', '钢笔', '6', '5999.00', '5999.00', '152', 'MONT BLANC 钢笔。', '5d1d6f1b63445900071514a9');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('24', '手表', '6', '17999.00', '17999.00', '53', 'MONT BLANC 手表。', '5d1d6f2863445900071514ab');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('25', '墨水', '6', '1799.00', '1799.00', '5234', 'MONT BLANC 墨水。', '5d1d6f3463445900071514ae');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('26', '华为 P30 Pro 手机', '7', '5799.00', '5799.00', '4243', '地表最强拍照手机。', '5d1d6fb263445900071514b0');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('27', '华为 MagicBook 2 笔记本电脑', '7', '8799.00', '8499.00', '6544', '华为 MagicBook 系列最新笔记本电脑。', '5d1d6fbf63445900071514b4');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('28', '华为手环 3', '7', '1499.00', '1399.00', '3426', '华为手环系列最新款。', '5d1d6fc963445900071514b6');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('29', '超能天然皂粉', '8', '59.00', '54.00', '42432', '超能天然皂粉去污牌洗衣粉。', '5d1d703363445900071514b9');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('30', '立白洗衣粉', '8', '34.00', '34.00', '64365', '立白最新款去污洗衣粉。', '5d1d703e63445900071514bb');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('31', '去渍霸超能去污洗衣粉', '8', '72.00', '72.00', '3147', '去渍霸，超能去污洗衣粉。', '5d1d704663445900071514bd');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('32', '汰渍洗衣液', '8', '59.00', '59.00', '4328', '汰渍牌强效去污洗衣液。', '5d1d705263445900071514bf');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('33', '蓝月亮护理洗衣液', '8', '49.00', '49.00', '8353', '蓝月亮不伤手护理洗衣液。', '5d1d705e63445900071514c1');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('34', '魅族 16 手机', '9', '2799.00', '2799.00', '5341', '魅族「Sixteen」系列手机。', '5d1d710363445900071514c3');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('35', '魅族 Note 6 手机', '9', '1499.00', '1499.00', '6241', '魅族大屏幕 Note 系列旗舰机。', '5d1d711063445900071514c5');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('36', '魅族 16X 手机', '9', '3099.00', '3099.00', '7426', '魅族“16”系列手机最新款。', '5d1d711a63445900071514c7');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('37', '茄子绒布玩具', '10', '15.00', '15.00', '47344', '茄子形状的绒布玩具，材质柔和，颜色亮丽。', '5d1d715d63445900071514c9');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('38', '冰激凌球绒布玩具', '10', '9.00', '9.00', '87692', '冰激凌球形状的绒布玩具，材质柔和，颜色亮丽。', '5d1d716863445900071514cb');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('39', '海盐洁面乳', '10', '97.00', '97.00', '1642', '包含真实海盐提取物的洁面乳。', '5d1d717263445900071514cd');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('40', 'SNICKERS 士力架巧克力', '11', '5.00', '5.00', '74124', '横扫饥饿的士力架巧克力条。', '5d1d71f363445900071514cf');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('41', '苹果', '11', '3.00', '3.00', '6276', '普通的山东寿光苹果。', '5d1d71fe63445900071514d1');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('42', '橙子', '11', '3.00', '3.00', '3784', '普通的甜橙。', '5d1d720b63445900071514d3');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('43', '金扶宁（外用重组人粒细胞巨噬细胞刺激因子凝胶剂）', '12', '5999.00', '5999.00', '51', '主要成分为重组人粒细胞巨噬细胞刺激因子（rhGM-CSF），辅料为羧甲基纤维素钠、甘油及保护剂。', '5d1d729c63445900071514d5');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('44', '美罗华（利妥昔单抗注射液）', '12', '3799.00', '3799.00', '57', '本品主要活性成分为重组利妥昔单抗。辅料包括枸橼酸钠，聚山梨醇酯，氯化钠和注射用水。', '5d1d72a763445900071514d7');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('45', '舒莱（巴利息单抗注射液）', '12', '12499.00', '12499.00', '13', '本品活性成份为巴利昔单抗。每瓶含巴利昔单抗20毫克或10毫克，配5毫升注射用水1支。', '5d1d72b263445900071514da');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('46', '拓益（特瑞普单抗注射液）', '12', '7399.00', '7399.00', '55', '每瓶含特瑞普利单抗240mg，通过DNA重组技术由中国仓鼠卵巢细胞制得。', '5d1d72bf63445900071514dc');

INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('47', 'Big Mac 巨无霸汉堡', '13', '45.00', '45.00', '312', '经典的 Big Mac 汉堡。', '5d1d732b63445900071514de');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('48', '炸薯条', '13', '15.00', '15.00', '345', '油炸土豆条。', '5d1d733a63445900071514e0');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('49', 'McFlurry 麦旋风冰激凌', '13', '17.00', '17.00', '897', '啊，是 McFlurry 麦旋风！', '5d1d734463445900071514e2');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('50', '双拼汉堡', '13', '16.00', '16.00', '434', '鸡肉和牛肉双拼汉堡。', '5d1d734f63445900071514e4');
INSERT INTO `eyulingo_db`.`Goods` (`good_id`, `good_name`, `store_id`, `price`, `discount`, `storage`, `description`, `good_image_id`) VALUES ('51', '炸面包条', '13', '9.00', '9.00', '231', '美式炸干面包条。', '5d1d735963445900071514e6');

INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('1', '手机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('1', '大屏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('1', 'iOS');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('1', '双摄像头');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('2', '平板电脑');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('2', 'iOS');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('2', 'A12X');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('3', '笔记本电脑');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('3', 'Mac');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('3', '新款');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('4', '手表');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('4', 'watchOS');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('4', '新品');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('4', '防水');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('5', '笔记本电脑');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('5', 'Surface');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('5', 'Windows');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('5', '二合一设备');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('6', 'Surface');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('6', 'Windows');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('6', '二合一设备');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('6', '独立显卡');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('7', '台式机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('7', 'Surface');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('7', '桌面工作站');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('7', '大屏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('7', '新品');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('8', '手机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('8', '大屏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('8', 'WP');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('8', '诺基亚');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('9', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('9', '猪排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('9', '芝士');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('9', '全家');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('10', '猪排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('10', '全家');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('10', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('11', '猪排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('11', '爆浆');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('11', '全家');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('11', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('12', '饮料');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('12', '罐装');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('12', '冷藏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('13', '鸡排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('13', '盖浇饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('13', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('13', '罗森');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('14', '麻辣');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('14', '香锅');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('14', '大碗');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('14', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('14', '罗森');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('15', '沙茶酱');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('15', '牛肉');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('15', '盖浇饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('15', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('15', '罗森');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('16', '饮料');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('16', '罐装');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('16', '冷藏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('17', '奶油');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('17', '虾仁');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('17', '意大利面');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('17', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('18', '咖喱');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('18', '鸡排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('18', '饭团');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('18', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('19', '海鲜');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('19', '披萨');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('19', '速冻');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('20', '海鲜');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('20', '烩饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('21', '麻婆豆腐');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('21', '烩饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('22', '鸡排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('22', '炭烤');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('22', '盒饭');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('23', '钢笔');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('23', '收藏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('24', '手表');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('24', '收藏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('25', '墨水');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('25', '钢笔');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('25', '收藏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('26', '手机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('26', '双摄');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('26', '拍照');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('26', '华为');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('27', '笔记本电脑');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('27', '华为');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('28', '手表');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('28', '华为');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('29', '超能');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('29', '洗衣粉');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('30', '立白');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('30', '洗衣粉');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('31', '去渍霸');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('31', '洗衣粉');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('32', '汰渍');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('32', '洗衣液');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('33', '蓝月亮');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('33', '洗衣液');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('34', '手机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('34', '魅族');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('35', '手机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('35', '魅族');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('35', '大屏');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('36', '魅族');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('36', '手机');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('36', '新品');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('37', '毛绒玩具');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('38', '毛绒玩具');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('39', '洁面乳');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('40', '巧克力');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('41', '水果');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('42', '水果');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('43', '金扶宁');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('43', '凝胶剂');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('44', '美罗华');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('44', '注射液');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('44', '单抗');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('45', '舒莱');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('45', '单抗');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('45', '注射液');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('46', '拓益');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('46', '单抗');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('46', '注射液');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('47', '牛肉');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('47', '汉堡');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('47', '巨无霸');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('48', '油炸');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('48', '薯条');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('49', '冰激凌');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('49', '麦旋风');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('50', '鸡排');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('50', '牛肉');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('50', '汉堡');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('51', '油炸');
INSERT INTO `eyulingo_db`.`Tags` (`good_id`, `tag_name`) VALUES ('51', '面包条');
