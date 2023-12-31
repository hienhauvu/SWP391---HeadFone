/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author quany
 */
public class Product {

    public String id;
    private String name;
    private float price;
    private String image;
    private int quantity;
    private String wire;
    private String description;

    private int discount;
    private float saleprice;
    private float avgrating;
    private String startdate;
    private String enddate;
    private String releasedate;

    private int tquantity;
    private float trevenue;

    public Product() {
        connect();
    }

    public Product(String id, String name, float price, int quantity, String releasedate, int tquantity, float trevenue) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.releasedate = releasedate;
        this.tquantity = tquantity;
        this.trevenue = trevenue;
    }

    public Product(String id, String name, float price, String image, int quantity, String wire, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.wire = wire;
        this.description = description;
    }

    public Product(String id, String name, float price, String image, int quantity, String wire, String description, int discount, float saleprice, float avgrating, String startdate, String enddate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.wire = wire;
        this.description = description;
        this.discount = discount;
        this.saleprice = saleprice;
        this.avgrating = avgrating;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Product(String id, String name, float price, String image, int quantity, String wire, String description, int discount, float saleprice, float avgrating, String startdate, String enddate, String releasedate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.wire = wire;
        this.description = description;
        this.discount = discount;
        this.saleprice = saleprice;
        this.avgrating = avgrating;
        this.startdate = startdate;
        this.enddate = enddate;
        this.releasedate = releasedate;
    }

    public int getTquantity() {
        return tquantity;
    }

    public void setTquantity(int tquantity) {
        this.tquantity = tquantity;
    }

    public float getTrevenue() {
        return trevenue;
    }

    public void setTrevenue(float trevenue) {
        this.trevenue = trevenue;
    }

    public String getWire() {
        return wire;
    }

    public void setWire(String wire) {
        this.wire = wire;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(float saleprice) {
        this.saleprice = saleprice;
    }

    public float getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(float avgrating) {
        this.avgrating = avgrating;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn != null) {
                //System.out.println("Connect successfully");
            } else {
                System.out.println("Connect Fail");
            }
        } catch (Exception e) {

        }
    }

    public List<Product> searchByName(String searchValue) {
        List<Product> list = new ArrayList<>();
        try {
            String strSelect = "select * from headphone.product where ProductName like ? OR Description like ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + searchValue + "%");
            pstm.setString(2, "%" + searchValue + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription));
            }
        } catch (Exception e) {
            System.out.println("searchByName" + e.getMessage());
        }
        return list;
    }

    public List<Product> searchProductByNameByPage(String searchValue, int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from headphone.product where ProductName like ? LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, "%" + searchValue + "%");
            pstm.setInt(2, page);
            pstm.setInt(3, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription));
            }
        } catch (Exception e) {
            System.out.println("searchProductByNameByPage" + e.getMessage());
        }
        return list;
    }

    public List<Product> searchProductByNameByPage2(String searchValue, int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL) AND (p.ProductName like ? OR p.Description like ?)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY p.ProductID\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, "%" + searchValue + "%");
            pstm.setString(2, "%" + searchValue + "%");
            pstm.setInt(3, page);
            pstm.setInt(4, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception e) {
            System.out.println("searchProductByNameByPage" + e.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductByPage(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM headphone.product LIMIT ? OFFSET ?";
            PreparedStatement statement = cnn.prepareStatement(sql);
            statement.setInt(1, page);
            statement.setInt(2, pageIndex);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductByPage2(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image,\n" +
                    "                           p.Quantity, p.WireWireless, p.Description, d.Discount,\n" +
                    "                           ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n" +
                    "                           COALESCE(avg(r.Rating), 0) as AverageRating,\n" +
                    "                           d.StartSaleDate, d.EndSaleDate, p.CategoryID\n" +
                    "                    FROM Headphone.Product p\n" +
                    "                    LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n" +
                    "                    LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n" +
                    "                    WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n" +
                    "                    GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n" +
                    "                             p.Quantity, p.WireWireless, p.Description, d.Discount, \n" +
                    "                             d.StartSaleDate, d.EndSaleDate \n"
                    + "LIMIT ? OFFSET ?";
            PreparedStatement statement = cnn.prepareStatement(sql);
            statement.setInt(1, page);
            statement.setInt(2, pageIndex);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM headphone.product";
            PreparedStatement statement = cnn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription));
            }
        } catch (Exception ex) {
            System.out.println("getAllProduct" + ex.getMessage());
        }
        return list;
    }
    //for back-end
    public Product getProductByID(String id) {
        try {
            String strSelect = "SELECT * FROM headphone.product where ProductID= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                return new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription);
            }
        } catch (Exception e) {
            System.out.println("getProductByID" + e.getMessage());
        }
        return null;
    }
    //display product curdate is not between startsaledate and endsaledate
    public Product getProductByID2(String id) {
        try {
            String strSelect = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID,p.ReleaseDate\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL) and p.ProductID like ?\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate,p.ReleaseDate\n"
                    + "ORDER BY p.ProductID;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                String release = f.format(rs.getDate(14));

                return new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end, release);
            }
        } catch (Exception ex) {
            System.out.println("getProductByID2" + ex.getMessage());
        }
        return null;
    }

    public Product getProductByID3(String id) {
        try {
            String strSelect = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "                           p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "                           ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "                           COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "                           d.StartSaleDate, d.EndSaleDate, p.CategoryID,p.ReleaseDate\n"
                    + "                    FROM Headphone.Product p\n"
                    + "                    LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "                    LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "                    WHERE p.ProductID like ?\n"
                    + "                    GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "                             p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "                             d.StartSaleDate, d.EndSaleDate,p.ReleaseDate\n"
                    + "                    ORDER BY p.ProductID;";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                String release = f.format(rs.getDate(14));

                return new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end, release);
            }
        } catch (Exception ex) {
            System.out.println("getProductByID2" + ex.getMessage());
        }
        return null;
    }

    public List<Product> getAllProductByCategoryByPage(int cid, int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from headphone.product where CategoryID= ? LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, cid);
            pstm.setInt(2, page);
            pstm.setInt(3, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription));
            }
        } catch (Exception e) {
            System.out.println("getAllProductByCategory" + e.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductByCategoryByPage2(int cid, int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL) AND p.CategoryID = ?\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY p.ProductID\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, cid);
            pstm.setInt(2, page);
            pstm.setInt(3, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception e) {
            System.out.println("getAllProductByCategory" + e.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductsByCategory(int cid) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM headphone.product where CategoryID = ?;";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, cid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription));
            }
        } catch (Exception e) {
            System.out.println("getAllProductByCategory" + e.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductsByCategory2(int cid) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL) and p.CategoryID = ?\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY p.ReleaseDate desc\n"
                    + "LIMIT 6";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, cid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, base64Image, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception e) {
            System.out.println("getAllProductByCategory" + e.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductSortByReleaseDate(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY p.ReleaseDate\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, page);
            pstm.setInt(2, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductSortByReleaseDateDesc(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY p.ReleaseDate desc\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, page);
            pstm.setInt(2, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductSortByRating(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY AverageRating\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, page);
            pstm.setInt(2, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductSortByRatingDesc(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "         d.StartSaleDate, d.EndSaleDate\n"
                    + "ORDER BY AverageRating desc\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, page);
            pstm.setInt(2, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductSortBySalePrice(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image,\n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "       case when d.Discount is not null then ((1 - d.Discount / 100) * p.ProductPrice) \n"
                    + "       else p.ProductPrice\n"
                    + "       end as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID, p.ReleaseDate\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image,\n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "         d.StartSaleDate, d.EndSaleDate, p.ReleaseDate\n"
                    + "ORDER BY SalePrice\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, page);
            pstm.setInt(2, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getAllProductSortBySalePriceDesc(int page, int pageIndex) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image,\n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "       case when d.Discount is not null then ((1 - d.Discount / 100) * p.ProductPrice) \n"
                    + "       else p.ProductPrice\n"
                    + "       end as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID, p.ReleaseDate\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image,\n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "         d.StartSaleDate, d.EndSaleDate, p.ReleaseDate\n"
                    + "ORDER BY SalePrice desc\n"
                    + "LIMIT ? OFFSET ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, page);
            pstm.setInt(2, pageIndex);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    public List<Product> getTrendingProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image,\n"
                    + "       p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "       ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "       COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "       d.StartSaleDate, d.EndSaleDate, p.CategoryID, p.ReleaseDate\n"
                    + "FROM Headphone.Product p\n"
                    + "LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE p.ProductID IN (\n"
                    + "  SELECT ProductID\n"
                    + "  FROM (\n"
                    + "    SELECT p.ProductID\n"
                    + "    FROM Headphone.Product as p\n"
                    + "    LEFT JOIN Headphone.OrderProduct AS op ON p.ProductID = op.ProductID\n"
                    + "    GROUP BY p.ProductID\n"
                    + "    order by COUNT(op.ProductID) desc\n"
                    + "    LIMIT 6\n"
                    + "  ) AS subquery\n"
                    + ")\n"
                    + "GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image,\n"
                    + "         p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "         d.StartSaleDate, d.EndSaleDate, p.ReleaseDate";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);

                //file image
                Blob imageBlob = rs.getBlob(4);
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());

                String base64Image = Base64.getEncoder().encodeToString(imageData);

                String Timage = base64Image;
                //

                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                String st = String.format("%.1f", rs.getFloat(10));
                float avg = Float.parseFloat(st);

                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return list;
    }

    //For add product
    public boolean isProductExists(String id) {
        // Sử dụng câu truy vấn SQL để kiểm tra sự tồn tại của sản phẩm với ID đã cho
        String query = "SELECT COUNT(*) FROM headphone.product WHERE ProductID = ?";
        try {
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean addProduct(String id, String name, float price, byte[] image, int quantity, String wire, String description, int cateId) {
        if (isProductExists(id)) {
            return false; // Sản phẩm đã tồn tại, không thêm sản phẩm mới
        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        Date sqlDate = new Date(utilDate.getTime());

        try {
            String strAdd = "INSERT INTO headphone.product (ProductID, ProductName, ProductPrice, image, Quantity, WireWireless, Description, CategoryID, ReleaseDate) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setFloat(3, price);

            pstm.setBytes(4, image);

            pstm.setInt(5, quantity);
            int wireValue = wire.equals("1") ? 1 : 0;
            pstm.setInt(6, wireValue);
            pstm.setString(7, description);
            pstm.setInt(8, cateId);

            pstm.setDate(9, sqlDate);

            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void updateProduct(String id, String name, float price, int quantity, String wire, String description) {
        try {
            String strUpdate = "update headphone.product set ProductName=?, ProductPrice=?,"
                    + " Quantity=?, WireWireless=?, Description=?  where ProductID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setFloat(2, price);
            pstm.setInt(3, quantity);
            if (wire.equals("Wired")) {
                pstm.setBoolean(4, true);
            } else {
                pstm.setBoolean(4, false);
            }
            pstm.setString(5, description);
            pstm.setString(6, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateProduct: " + e.getMessage());
        }
    }

    public void updateImageProduct(byte[] image, String id) {
        try {
            String sql = "UPDATE `headphone`.`product` SET `image` = ? WHERE (`ProductID` like ?);";
            pstm = cnn.prepareStatement(sql);
            pstm.setBytes(1, image);
            pstm.setString(2, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateProduct2: " + e.getMessage());
        }
    }

    public void updateHistory(String changes, String ProductID) {
        try {
            String strUpdate = "insert into headphone.historyproduct (Changes, UpdateDate, ProductID) values (?, ?, ?)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, changes);

            java.util.Calendar cal = java.util.Calendar.getInstance();
            java.util.Date utilDate = cal.getTime();
            Date sqlDate = new Date(utilDate.getTime());
            pstm.setDate(2, sqlDate);

            pstm.setString(3, ProductID);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateHistory: " + e.getMessage());
        }
    }

    public boolean checkDiscount(String id) {
        try {
            String strSelect = "SELECT * FROM headphone.discount where ProductID=? and EndSaleDate >= CURDATE()";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkDiscount:" + e.getMessage());
        }
        return false;
    }

    public List<Product> getAllProductReleaseDate() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "                           p.Quantity, p.WireWireless, p.Description, d.Discount,\n"
                    + "                           ((1 - d.Discount / 100) * p.ProductPrice) as SalePrice,\n"
                    + "                           COALESCE(avg(r.Rating), 0) as AverageRating,\n"
                    + "                           d.StartSaleDate, d.EndSaleDate, p.ReleaseDate\n"
                    + "                    FROM Headphone.Product p\n"
                    + "                    LEFT JOIN Headphone.Discount d ON p.ProductID = d.ProductID\n"
                    + "                    LEFT JOIN Headphone.Review r ON p.ProductID = r.ProductID\n"
                    + "WHERE ((curdate() BETWEEN d.StartSaleDate AND d.EndSaleDate) OR d.Discount IS NULL)\n"
                    + "                    GROUP BY p.ProductID, p.ProductName, p.ProductPrice, p.image, \n"
                    + "                            p.Quantity, p.WireWireless, p.Description, d.Discount, \n"
                    + "                          d.StartSaleDate, d.EndSaleDate\n"
                    + "                    ORDER BY d.StartSaleDate desc";
            PreparedStatement statement = cnn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String Tid = rs.getString(1);
                String Tname = rs.getString(2);
                float Tprice = rs.getFloat(3);
                String Timage = rs.getString(4);
                int Tquantity = rs.getInt(5);
                String Twire = "Wired";
                if (rs.getInt(6) == 0) {
                    Twire = "Wireless";
                }
                String Tdescription = rs.getString(7);

                int dis = 0;
                float sprice = 0;
                if (rs.getString(8) != null) {
                    dis = rs.getInt(8);
                    sprice = rs.getFloat(9);
                }

                float avg = rs.getFloat(10);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String start = "";
                if (rs.getString(11) != null) {
                    start = f.format(rs.getDate(11));
                }
                String end = "";
                if (rs.getString(12) != null) {
                    end = f.format(rs.getDate(12));
                }
                String rdate = "";
                if (rs.getString(13) != null) {
                    rdate = f.format(rs.getDate(13));
                }

                list.add(new Product(Tid, Tname, Tprice, Timage, Tquantity, Twire, Tdescription, dis, sprice, avg, start, end, rdate));
            }
        } catch (Exception ex) {
            System.out.println("getAllProductReleaseDate" + ex.getMessage());
        }
        return list;
    }

    public void setDiscount(String start, String end, int discount, String id) {
        try {
            String add = "insert into headphone.discount (StartSaleDate, EndSaleDate, Discount, ProductID) values (?, ?, ?, ?)";
            pstm = cnn.prepareStatement(add);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(start);
            System.out.println("setDiscount: " + parsed);
            Date sql = new Date(parsed.getTime());
            pstm.setDate(1, sql);
            parsed = format.parse(end);
            sql = new Date(parsed.getTime());
            pstm.setDate(2, sql);

            pstm.setInt(3, discount);
            pstm.setString(4, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("setDiscount: " + e.getMessage());
        }
    }

    public int getNumberOfProduct() {
        try {
            String sql = "SELECT COUNT(*) AS ProductCount FROM Headphone.Product;";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("getAllProductByPage" + ex.getMessage());
        }
        return -1;
    }

    public void delete(String id) {
        String strSelect = "UPDATE `headphone`.`product` SET `Quantity` = '-1' WHERE (`ProductID` = ?);";
        try {
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            int rowsAffected = pstm.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteDiscount(String id) {
        try {
            String strSelect = "delete from headphone.discount where ProductID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteDiscount: " + e.getMessage());
        }
    }

    public List<Product> getListTotalRevenueProduct(){
        List<Product> list = new ArrayList<>();
        try{
            String sql = "SELECT p.ProductID,p.ProductName,p.Quantity,p.ReleaseDate,p.ProductPrice,SUM(o.Quantity) AS TotalQuantity,SUM(p.ProductPrice * o.Quantity) AS TotalRevenue\n" +
                    "FROM Headphone.Product p\n" +
                    "JOIN Headphone.OrderProduct o ON p.ProductID = o.ProductID\n" +
                    "GROUP BY p.ProductID, p.ProductName, p.ProductPrice;";
            PreparedStatement statement = cnn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String pid = rs.getString(1);
                String pname = rs.getString(2);
                int pquantity = rs.getInt(3);
                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                String pdate = f.format(rs.getDate(4));
                float price = rs.getFloat(5);
                int tquantity = rs.getInt(6);
                float revenue = rs.getFloat(7);

                list.add(new Product(pid, pname, price, pquantity, pdate, tquantity, revenue));
            }
        }catch(Exception e){
            System.out.println("getListTotalRevenueProduct: " + e.getMessage());
        }
        return list;
    }
}

