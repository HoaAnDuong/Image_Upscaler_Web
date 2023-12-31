A huge shout out for xinntao for [xinntao/ESRGAN](https://github.com/xinntao/ESRGAN)

Mã nguồn: [HoaAnDuong/Image_Upscaler_Web](https://github.com/HoaAnDuong/Image_Upscaler_Web)

1.	Giới thiệu

Để phục vụ cho bài tập lớn cuối kỳ về 1 hệ thống server JSP/Servlet có xử lý tính toán lớn, chúng em đã chọn việc triển khai 1 trang web Upscale Ảnh bằng công nghệ ESRGAN.

Với sự phát triển như vũ bão của Trí Tuệ Nhân Tạo, đặc biệt là Mạng nơ ron thần kinh và Học sâu, thì các trang web cung cấp các dịch vụ về Trí Tuệ Nhân Tạo như đang ngày phổ biến với đại chúng, trong đó có thể kể đén các trang web Upscale Ảnh. Các trang web Upscale Ảnh bằng AI sử dụng các công nghê liên quan đến Mạng nơ ron thần kinh và Học sâu để tạo ra ảnh có độ phân giải cao hơn và tái tạo lại sắc nét các chi tiết không rõ từ những bức ảnh gốc được tải lên bởi người dùng. Hãy tưởng tượng bạn đã kiếm được 1 hình nền ưng mắt nhưng độ phân giải lại không đủ để làm hình nền cho máy tính bạn, hay 1 chiếc meme cực kì ngầu trên Facebook nhưng đã bị mờ đi quá nnhieeuf do bị chia sẻ, truyền tay nhau liên tục trên các diễn đàn. Đó là lúc bạn cần 1 trang web để Upscale Ảnh.

Về các công nghệ Upscale Ảnh, có rất nhiều model upscale ảnh với chất lượng cực kì tốt như HAT-L, EDT-B, SwinIR,... Tuy nhiên các model trên có kích thước rất lớn và đòi hỏi phần cứng phải đủ mạnh để chạy chúng. Vì vậy trong phạm vi bài tập lớn này, tụi em sẽ sử dụng model [ESRGAN](https://github.com/xinntao/ESRGAN), một Model tạo sinh có kích thước nhỏ, tuy nhiên vẫn có khả năng upscale ảnh lên đến 4 lần với chất lượng ảnh tái tạo lại khá tốt.

2.	Cài đặt
   
a.	Cài đặt môi trường

Tiến hành cài đặt các thư viện và môi trường sau:
-	Eclipse EE: [Eclipse IDE for Java EE Developers | Eclipse Packages (Có thể thay thế bằng Eclipse Bình thường với Dynamic Web Module được cài đặt trên Marketplace)](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)
-	JDK 17: [Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/#java17)
-	Python 3: [Download Python | Python.org](https://www.python.org/downloads/)
-	MySQL (Server và Workbench): [MySQL :: MySQL Downloads](https://www.mysql.com/downloads/)
-	Tomcat 10.1: [Apache Tomcat 10 Software Downloads](https://tomcat.apache.org/download-10.cgi)
-	MySQL JDBC: [MySQL :: Download Connector/J](https://dev.mysql.com/downloads/connector/j/)
-	JSON Simple: [JSON Simple](https://code.google.com/archive/p/json-simple/)

b.	Tiến hành triển khai hệ thống

Database

-	Mở MySQL Workbench
-	Import image_upscaler.sql trong Thư mục Project

Server JSP/Servlet:

-	Clone Mã nguồn Project từ GitHub: https://github.com/HoaAnDuong/Image_Upscaler_ESRGAN
-	Vào Folder trên, chạy setup.bat
-	Tiến hành Import Project trên Eclipse
-	Mở Properties, chọn Java Build Path, chỉnh sửa môi trường chạy cho Java và thêm JAR của MySQL JDBC và JSON Simple
-	Chỉnh sửa các thông tin liên quan đến kết nối MySQL trong Models/DAO.java
-	Tạo Server mới từ Tomcat 10.1 đã tải xuống
-	Chạy server.
-	Thực thi file Execution/Image_Upscaler.java để bắt đầu chương trình gọi tiến trình Python để Upscale Ảnh.

3. Cấu trúc Hệ thống
   
![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/System%20Diagram.jpg?raw=true)

4. CSDL

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/Database%20Diagram.png?raw=true)

5. Kết quả chạy chương trình

a. Ở phía Client
Trang đăng nhập

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Login.png?raw=true)

Giao diện chính

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Main%20page.png?raw=true)

Upload ảnh

- Nhấn Upscale Image để mở form upload ảnh, sau đó chọn file và điều chỉnh các tùy chọn và nhấn Upload.

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Image%20Upload.png?raw=true)

Đợi Tiến trình Upscale Ảnh

- Các Task đang đợi để được server xử lý

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Before%20Upscaling.png?raw=true)

- 3 tiến trình đang Upscale 3 ảnh cùng 1 lúc
![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Image%20is%20processed.png?raw=true)

- 3 ảnh đầu đã xong, 3 tiến trình khác được gọi ra để upscale tiếp.
![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Some%20Task%20done.png?raw=true)

- Upscale thành công
![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/All%20Tasks%20done.png?raw=true)

Kết quả

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Origin%20vs%20Upscaled%202.png)

b. Trang Admin

- Thêm người dùng

![](https://raw.githubusercontent.com/HoaAnDuong/Image_Upscaler_Web/main/admin%20site%20images/Add%20User.png)

- Sau khi thêm người dùng

![](https://raw.githubusercontent.com/HoaAnDuong/Image_Upscaler_Web/main/admin%20site%20images/Admin%20Site%201.png)

- Chỉnh sửa thông tin người dùng

![](https://raw.githubusercontent.com/HoaAnDuong/Image_Upscaler_Web/main/admin%20site%20images/Update%20User.png)

- Sau khi chỉnh sửa thông tin người dùng

![](https://raw.githubusercontent.com/HoaAnDuong/Image_Upscaler_Web/main/admin%20site%20images/Admin%20Site%202.png)

- Thanh tìm kiếm

![](https://raw.githubusercontent.com/HoaAnDuong/Image_Upscaler_Web/main/admin%20site%20images/Admin%20Site%203.png)

c. Quá trình chạy(ở phía Server)

Ở Server, JSP/Servlet sẽ đảm nhận vai trò xử lý các request và trả về những tài nguyên cần thiết cho Client như ảnh và thông tin về những task người dùng upload lên.

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Servlet%20Example.png)

Bên cạnh đó, 1 chương trình Java sẽ được chạy ngầm(độc lập với Server). Chương trình này sẽ gọi ra các Workers.
Các worker sẽ chọn các Task được người dùng upload lên(ưu tiên các task pending và đươc upload lên trước), sau đó tiến hành xử lý và gọi các tiến trình thực thi chương trình Python để upscale ảnh

Để tránh việc các worker chọn trùng task(gây lãng phí tài nguyên), thì các worker buộc phải dừng hoạt động khi không có task, và phải đợi worker đứng trước có task trước thì mới
được chọn task khác

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Image%20Upscaler%20Execution.png)

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Aftermath.png)

d. Kết quả

Nhìn chung, ESRGAN cho ra kết quả không tốt lắm. Tuy nhiên với 1 model chỉ có kích thước 63 MB và ảnh đầu vào có độ phân giải thấp thì kết quả như vậy là khá ổn.

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Original%20vs%20Upscaled%201.png)

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Origin%20vs%20Upscaled%202.png)

![](https://github.com/HoaAnDuong/Image_Upscaler_Web/blob/main/web%20results/Origin%20vs%20Upscaled%203.png)
