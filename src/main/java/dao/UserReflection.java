package dao;

import annotation.UserAnnotation;
import database.JDBCUtil;
import model.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserReflection implements DAOInterface<User> {

    public static UserReflection getInstance(){
        return new UserReflection();
    }

    @Override
    public int insert(User user) {
        int result = 0;
        try{
        Connection connection = JDBCUtil.getConnection();
        Class<?> listClass = user.getClass();
        Field [] fields = listClass.getDeclaredFields();
        int idUser = 0;
        String username = "";
        String password = "";
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals("id")) {
                    idUser = (int) field.get(user);
                }
                if (field.getName().equals("username")) {
                    username = (String) field.get(user);
                }
                if (field.getName().equals("password")) {
                    password = (String) field.get(user);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Class userClass= User.class;
        Annotation annotation = userClass.getAnnotation(UserAnnotation.class);
        UserAnnotation userAnnotation = null;
        if (annotation instanceof UserAnnotation) {
            userAnnotation = (UserAnnotation) annotation;
        }

        String sql = "INSERT INTO teststatement.user_infomation (UserID, Username, Password) "+
                " VALUES ('"+idUser+"' , '"+username+"' , '"+ password+"')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        result = preparedStatement.executeUpdate();
        System.out.println("Bạn đã thực thi: "+ sql);
        System.out.println("Có "+ result+" dòng bị thay đổi!");

//        JDBCUtil.printInfo(connection);
        JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();


        }
        return result;
    }

    @Override
    public int update(User user) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            Class<?> listClass = user.getClass();
            Field [] fields = listClass.getDeclaredFields();
            int idUser = 0;
            String username = "";
            String password = "";
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.getName().equals("id")) {
                        idUser = (int) field.get(user);
                    }
                    if (field.getName().equals("username")) {
                        username = (String) field.get(user);
                    }
                    if (field.getName().equals("password")) {
                        password = (String) field.get(user);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            Class userClass= User.class;
            Annotation annotation = userClass.getAnnotation(UserAnnotation.class);
            UserAnnotation userAnnotation = null;
            if (annotation instanceof UserAnnotation) {
                userAnnotation = (UserAnnotation) annotation;
            }

            String sql = "UPDATE teststatement.user_infomation SET Username = '"+username+"', Password = '"+password+"' WHERE UserID = '"+idUser+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

//            JDBCUtil.printInfo(connection);
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(User user) {
        int result = 0;
try {
            Connection connection = JDBCUtil.getConnection();
            Class<?> listClass = user.getClass();
            Field [] fields = listClass.getDeclaredFields();
            int idUser = 0;
            String username = "";
            String password = "";
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.getName().equals("id")) {
                        idUser = (int) field.get(user);
                    }
                    if (field.getName().equals("username")) {
                        username = (String) field.get(user);
                    }
                    if (field.getName().equals("password")) {
                        password = (String) field.get(user);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            Class userClass= User.class;
            Annotation annotation = userClass.getAnnotation(UserAnnotation.class);
            UserAnnotation userAnnotation = null;
            if (annotation instanceof UserAnnotation) {
                userAnnotation = (UserAnnotation) annotation;
            }

            String sql = "DELETE FROM teststatement.user_infomation WHERE UserID = '"+idUser+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            result = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ result+" dòng bị thay đổi!");

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return result;
    }

    @Override
    public User selectById(int id) {
        User result = null;
        try{
            Connection connection = JDBCUtil.getConnection();
            Class userClass= User.class;
            Annotation annotation = userClass.getAnnotation(UserAnnotation.class);
            UserAnnotation userAnnotation = null;
            if (annotation instanceof UserAnnotation) {
                userAnnotation = (UserAnnotation) annotation;
            }

            String sql = "SELECT * FROM teststatement.user_infomation WHERE UserID = '"+id+"'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                result = new User(idUser, username, password);
            }
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có kết quả là : "+ result);


        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<User> sellectAll(){
        List<User> result = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Class userClass= User.class;
            Annotation annotation = userClass.getAnnotation(UserAnnotation.class);
            UserAnnotation userAnnotation = null;
            if (annotation instanceof UserAnnotation) {
                userAnnotation = (UserAnnotation) annotation;
            }

            String sql = "SELECT * FROM teststatement.user_infomation";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                result.add(new User(idUser, username, password));
            }
            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có kết quả là : ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
