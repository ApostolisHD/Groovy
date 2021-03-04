import groovy.sql.Sql;

def url = 'jdbc:postgresql://localhost:5432/employee_databse';
def user = 'postgres';
def password = '123456';
def driver = 'org.postgresql.Driver';
def sql = Sql.newInstance(url, user, password, driver);

println "id"+ " " + "name" + " " + "dob";
def query = "select * from employee";
sql.eachRow query, {employee -> println employee.id + " " + employee.name + " " + employee.dob};

sql.close();
