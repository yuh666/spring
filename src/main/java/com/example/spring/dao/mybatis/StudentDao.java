package com.example.spring.dao.mybatis;


import com.example.spring.common.TargetDatasource;
import com.example.spring.enums.DatasourceEnum;
import com.example.spring.po.Query;
import com.example.spring.po.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

// 1.JDK8参数可以不写@Param直接取到参数名
// 2.单个传list的话必须用list取
// 3.多层的话才用list的变量名
// 4.总结就是 取传过来的的内部的第一层
@Repository
@TargetDatasource(DatasourceEnum.BCC_3306)
public interface StudentDao {


    @TargetDatasource(DatasourceEnum.BCC_3306)
    long insert(Student student);

    @TargetDatasource(DatasourceEnum.BCC_3306)
    int update(Student student);

    @TargetDatasource(DatasourceEnum.BCC_3306)
    List<Student> selectByNameAndAge(String name, Integer age);

    @TargetDatasource(DatasourceEnum.BCC_3306)
    List<Student> selectByNames(List<String> names, Integer age);

    @TargetDatasource(DatasourceEnum.BCC_3306)
    List<Student> selectByQuery(Query query, int age);

    @TargetDatasource(DatasourceEnum.BCC_3306)
    List<Student> selectAll();

    @TargetDatasource(DatasourceEnum.BCC_3306)
    void insertBatch(@Param("students") List<Student> students);

    @TargetDatasource(DatasourceEnum.BCC_3306)
    @SelectProvider(type = Provider.class, method = "selectByQueryAnno")
    List<Student> selectByQueryAnno(Query query, int age, int part);


    class Provider {
        public String selectByQueryAnno(Map<String, Object> para) {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from student_#{part} where 1=1 ");
            Integer age = (Integer) para.get("age");
            Query query = (Query) para.get("query");
            if (query.getName() != null) {
                sb.append("and name = #{query.name}");
            }
            if (age != null && age > 0) {
                sb.append("and age = #{age}");
            }
            return sb.toString();
        }
    }
}
