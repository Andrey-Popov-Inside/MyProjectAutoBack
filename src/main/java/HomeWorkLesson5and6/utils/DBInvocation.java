package HomeWorkLesson5and6.utils;

import HomeWorkLesson5and6.servises.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBInvocation {
    static String resource = "";
    static SqlSessionFactory sqlSessionFactory = null;


    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Integer countNumberOfAllCategories(String resource) throws IOException {
        db.dao.CategoriesMapper categoriesMapper = getCategoriesMapper(resource);
        db.model.CategoriesExample example = new db.model.CategoriesExample();
        return Math.toIntExact(categoriesMapper.countByExample(example));
    }

    public static db.dao.CategoriesMapper getCategoriesMapper(String resource) throws IOException {

        SqlSession session = sqlSessionFactory.openSession();
        return session.getMapper(db.dao.CategoriesMapper.class);

    }

    public static String getTitleById(int id) throws IOException {

        db.dao.CategoriesMapper categoriesMapper = getCategoriesMapper(resource);
        return categoriesMapper.selectByPrimaryKey((long) id).getTitle();

    }

    public static void createNewProduct(Product product) throws IOException {

        db.dao.ProductsMapper productsMapper = getProductsMapper(resource);
        db.model.Products products = new db.model.Products();
        products.setId((long) (int) product.getId());
        products.setPrice(product.getPrice());
        products.setCategory_id(1L);
        products.setTitle(product.getTitle());
        System.out.println(" разделительная черта");
        System.out.println(product.getId() + " " + product.getPrice() + " " + product.getTitle());
        productsMapper.insert(products);

    }

    public static void updateProduct(Product product) throws IOException {

        db.dao.ProductsMapper productsMapper = getProductsMapper(resource);
        db.model.Products products = new db.model.Products();
        products.setId((long) (int) product.getId());
        products.setPrice(product.getPrice());
        products.setCategory_id(1L);

        products.setTitle(product.getTitle());
        productsMapper.updateByPrimaryKeySelective(products);

    }

    public static void deleteProduct(int id) throws IOException {

        db.dao.ProductsMapper productsMapper = getProductsMapper(resource);
        productsMapper.deleteByPrimaryKey((long) id);

    }

    public static Product getProductById(int id) throws IOException {

        db.dao.ProductsMapper productsMapper = getProductsMapper(resource);
        db.model.Products products = productsMapper.selectByPrimaryKey((long) id);
        System.out.println(products.getId());
        Product product = new Product();

        return product.withId((int) (long) products
                        .getId())
                .withPrice(products.getPrice())
                .withTitle(products.getTitle())
                .withCategoryTitle("Food");

    }


    public static db.dao.ProductsMapper getProductsMapper(String resource) throws IOException {

        SqlSession session = sqlSessionFactory.openSession();
        return session.getMapper(db.dao.ProductsMapper.class);

    }
}
