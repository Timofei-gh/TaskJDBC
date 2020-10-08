package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.saveUser("Алексей", "Смирнов", (byte) 35);

        userDao.saveUser("Андрей", "Сидоров", (byte) 25);

        userDao.saveUser("Антон", "Федоров", (byte) 30);

        userDao.saveUser("Иван", "Петров", (byte) 40);

        System.out.println(userDao.getAllUsers().toString());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();



    }
}
