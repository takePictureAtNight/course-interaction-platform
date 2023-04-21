package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 调研数据表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-21 10:21:44
 */
@Data
@TableName("survey_data")
public class SurveyDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Integer a1;
    /**
     *
     */
    private Integer a2;
    /**
     *
     */
    private String a22;
    /**
     *
     */
    private Integer a3;
    /**
     *
     */
    private Integer a4;
    /**
     *
     */
    private Integer a5year;
    /**
     *
     */
    private Integer a5month;
    /**
     *
     */
    private Integer a5date;
    /**
     *
     */
    private Integer a6;
    /**
     *
     */
    private Integer a7;
    /**
     *
     */
    private Integer a8;
    /**
     *
     */
    private String a89;
    /**
     *
     */
    private Integer a9;
    /**
     *
     */
    private Integer a10;
    /**
     *
     */
    private Integer b1;
    /**
     *
     */
    private Integer b11;
    /**
     *
     */
    private Integer b12;
    /**
     *
     */
    private Integer b13;
    /**
     *
     */
    private Integer b21;
    /**
     *
     */
    private Integer b22;
    /**
     *
     */
    private Integer b3;
    /**
     *
     */
    private Integer b41;
    /**
     *
     */
    private Integer b42;
    /**
     *
     */
    private Integer b43;
    /**
     *
     */
    private Integer b44;
    /**
     *
     */
    private Integer b45;
    /**
     *
     */
    private Integer b46;
    /**
     *
     */
    private Integer b47;
    /**
     *
     */
    private String b48;
    /**
     *
     */
    private Integer c11;
    /**
     *
     */
    private Integer c12;
    /**
     *
     */
    private Integer c13;
    /**
     *
     */
    private Integer c14;
    /**
     *
     */
    private Integer c15;
    /**
     *
     */
    private Integer c16;
    /**
     *
     */
    private String c17;
    /**
     *
     */
    private Integer c2;
    /**
     *
     */
    private Integer c31;
    /**
     *
     */
    private Integer c32;
    /**
     *
     */
    private Integer c33;
    /**
     *
     */
    private Integer c34;
    /**
     *
     */
    private Integer c35;
    /**
     *
     */
    private Integer c36;
    /**
     *
     */
    private Integer c37;
    /**
     *
     */
    private Integer c38;
    /**
     *
     */
    private String c39;
    /**
     *
     */
    private Integer c4;
    /**
     *
     */
    private Integer d11;
    /**
     *
     */
    private Integer d12;
    /**
     *
     */
    private Integer d13;
    /**
     *
     */
    private Integer d14;
    /**
     *
     */
    private Integer d15;
    /**
     *
     */
    private Integer d16;
    /**
     *
     */
    private Integer d17;
    /**
     *
     */
    private Integer d18;
    /**
     *
     */
    private Integer d19;
    /**
     *
     */
    private String d110;
    /**
     *
     */
    private Integer d111;
    /**
     *
     */
    private Integer d21;
    /**
     *
     */
    private Integer d22;
    /**
     *
     */
    private Integer d23;
    /**
     *
     */
    private Integer d24;
    /**
     *
     */
    private Integer d25;
    /**
     *
     */
    private Integer d26;
    /**
     *
     */
    private Integer d27;
    /**
     *
     */
    private Integer d28;
    /**
     *
     */
    private Integer d29;
    /**
     *
     */
    private Integer d210;
    /**
     *
     */
    private Integer d211;
    /**
     *
     */
    private Integer d212;
    /**
     *
     */
    private Integer d213;
    /**
     *
     */
    private Integer d214;
    /**
     *
     */
    private Integer d215;
    /**
     *
     */
    private Integer d3;
    /**
     *
     */
    private String d35;
    /**
     *
     */
    private Integer e011;
    /**
     *
     */
    private Integer e012;
    /**
     *
     */
    private Integer e013;
    /**
     *
     */
    private Integer e014;
    /**
     *
     */
    private Integer e021;
    /**
     *
     */
    private Integer e022;
    /**
     *
     */
    private Integer e023;
    /**
     *
     */
    private Integer e024;
    /**
     *
     */
    private Integer e031;
    /**
     *
     */
    private Integer e032;
    /**
     *
     */
    private Integer e033;
    /**
     *
     */
    private Integer e034;
    /**
     *
     */
    private Integer e041;
    /**
     *
     */
    private Integer e042;
    /**
     *
     */
    private Integer e043;
    /**
     *
     */
    private Integer e044;
    /**
     *
     */
    private Integer e051;
    /**
     *
     */
    private Integer e052;
    /**
     *
     */
    private Integer e053;
    /**
     *
     */
    private Integer e054;
    /**
     *
     */
    private Integer e061;
    /**
     *
     */
    private Integer e062;
    /**
     *
     */
    private Integer e063;
    /**
     *
     */
    private Integer e064;
    /**
     *
     */
    private Integer e071;
    /**
     *
     */
    private Integer e072;
    /**
     *
     */
    private Integer e073;
    /**
     *
     */
    private Integer e074;
    /**
     *
     */
    private Integer e081;
    /**
     *
     */
    private Integer e082;
    /**
     *
     */
    private Integer e083;
    /**
     *
     */
    private Integer e084;
    /**
     *
     */
    private Integer e11;
    /**
     *
     */
    private Integer e12;
    /**
     *
     */
    private String e124;
    /**
     *
     */
    private Integer e131;
    /**
     *
     */
    private Integer e132;
    /**
     *
     */
    private Integer e133;
    /**
     *
     */
    private Integer e134;
    /**
     *
     */
    private Integer e14;
    /**
     *
     */
    private Integer e15;
    /**
     *
     */
    private Integer e211;
    /**
     *
     */
    private Integer e212;
    /**
     *
     */
    private Integer e213;
    /**
     *
     */
    private Integer e214;
    /**
     *
     */
    private Integer e215;
    /**
     *
     */
    private Integer e216;
    /**
     *
     */
    private Integer e217;
    /**
     *
     */
    private Integer e22;
    /**
     *
     */
    private Integer e31;
    /**
     *
     */
    private Integer e32;
    /**
     *
     */
    private Integer e331;
    /**
     *
     */
    private Integer e332;
    /**
     *
     */
    private Integer e333;
    /**
     *
     */
    private Integer e334;
    /**
     *
     */
    private Integer e335;
    /**
     *
     */
    private Integer e336;
    /**
     *
     */
    private Integer e337;
    /**
     *
     */
    private Integer e338;
    /**
     *
     */
    private String e339;
    /**
     *
     */
    private Integer e34;
    /**
     *
     */
    private Integer e35;
    /**
     *
     */
    private Integer e36;
    /**
     *
     */
    private Integer e371;
    /**
     *
     */
    private Integer e372;
    /**
     *
     */
    private Integer e373;
    /**
     *
     */
    private Integer e374;
    /**
     *
     */
    private Integer e375;
    /**
     *
     */
    private Integer e38;
    /**
     *
     */
    private Integer e411;
    /**
     *
     */
    private Integer e412;
    /**
     *
     */
    private Integer e413;
    /**
     *
     */
    private Integer e414;
    /**
     *
     */
    private Integer e415;
    /**
     *
     */
    private Integer e416;
    /**
     *
     */
    private Integer e417;
    /**
     *
     */
    private Integer e418;
    /**
     *
     */
    private Integer e419;
    /**
     *
     */
    private Integer e4110;
    /**
     *
     */
    private Integer e4111;
    /**
     *
     */
    private String e420;
    /**
     *
     */
    private Integer e421;
    /**
     *
     */
    private Integer e422;
    /**
     *
     */
    private Integer e423;
    /**
     *
     */
    private String e424;
    /**
     *
     */
    private Integer e43;
    /**
     *
     */
    private Integer e511;
    /**
     *
     */
    private Integer e512;
    /**
     *
     */
    private Integer e513;
    /**
     *
     */
    private Integer e514;
    /**
     *
     */
    private Integer e515;
    /**
     *
     */
    private Integer e516;
    /**
     *
     */
    private Integer e517;
    /**
     *
     */
    private Integer e518;
    /**
     *
     */
    private String e519;
    /**
     *
     */
    private Integer e5110;
    /**
     *
     */
    private Integer e52;
    /**
     *
     */
    private Integer e53;
    /**
     *
     */
    private String e534;
    /**
     *
     */
    private Integer e541;
    /**
     *
     */
    private Integer e543;
    /**
     *
     */
    private Integer e544;
    /**
     *
     */
    private Integer e545;
    /**
     *
     */
    private Integer e546;
    /**
     *
     */
    private Integer e547;
    /**
     *
     */
    private Integer e548;
    /**
     *
     */
    private Integer e549;
    /**
     *
     */
    private Integer e5410;
    /**
     *
     */
    private Integer e5411;
    /**
     *
     */
    private Integer e5412;
    /**
     *
     */
    private Integer e5413;
    /**
     *
     */
    private String e5414;
    /**
     *
     */
    private Integer e55;
    /**
     *
     */
    private Integer e611;
    /**
     *
     */
    private Integer e612;
    /**
     *
     */
    private Integer e613;
    /**
     *
     */
    private Integer e614;
    /**
     *
     */
    private Integer e615;
    /**
     *
     */
    private Integer e616;
    /**
     *
     */
    private Integer e617;
    /**
     *
     */
    private Integer e618;
    /**
     *
     */
    private String e619;
    /**
     *
     */
    private Integer e6110;
    /**
     *
     */
    private Integer e621;
    /**
     *
     */
    private Integer e622;
    /**
     *
     */
    private Integer e623;
    /**
     *
     */
    private Integer e624;
    /**
     *
     */
    private Integer e625;
    /**
     *
     */
    private String e626;
    /**
     *
     */
    private Integer e63;
    /**
     *
     */
    private Integer e711;
    /**
     *
     */
    private Integer e712;
    /**
     *
     */
    private Integer e713;
    /**
     *
     */
    private Integer e714;
    /**
     *
     */
    private Integer e715;
    /**
     *
     */
    private Integer e716;
    /**
     *
     */
    private Integer e717;
    /**
     *
     */
    private Integer e718;
    /**
     *
     */
    private Integer e719;
    /**
     *
     */
    private Integer e17111;
    /**
     *
     */
    private Integer e7112;
    /**
     *
     */
    private Integer e7113;
    /**
     *
     */
    private Integer e7114;
    /**
     *
     */
    private Integer e7115;
    /**
     *
     */
    private String e7116;
    /**
     *
     */
    private Integer e7117;
    /**
     *
     */
    private Integer e721;
    /**
     *
     */
    private Integer e722;
    /**
     *
     */
    private Integer e723;
    /**
     *
     */
    private Integer e724;
    /**
     *
     */
    private Integer e725;
    /**
     *
     */
    private Integer e726;
    /**
     *
     */
    private String e727;
    /**
     *
     */
    private Integer e731;
    /**
     *
     */
    private Integer e732;
    /**
     *
     */
    private Integer e733;
    /**
     *
     */
    private String e734;
    /**
     *
     */
    private Integer e735;
    /**
     *
     */
    private Integer e741;
    /**
     *
     */
    private Integer e742;
    /**
     *
     */
    private Integer e743;
    /**
     *
     */
    private Integer e744;
    /**
     *
     */
    private Integer e745;
    /**
     *
     */
    private Integer e746;
    /**
     *
     */
    private Integer e747;
    /**
     *
     */
    private Integer e748;
    /**
     *
     */
    private Integer e749;
    /**
     *
     */
    private Integer e7410;
    /**
     *
     */
    private Integer e7411;
    /**
     *
     */
    private String e7412;
    /**
     *
     */
    private Integer e75;
    /**
     *
     */
    private Integer e76;
    /**
     *
     */
    private Integer e81;
    /**
     *
     */
    private Integer e821;
    /**
     *
     */
    private Integer e822;
    /**
     *
     */
    private Integer e823;
    /**
     *
     */
    private Integer e824;
    /**
     *
     */
    private Integer e825;
    /**
     *
     */
    private String e26;
    /**
     *
     */
    private Integer e827;
    /**
     *
     */
    private Integer e83;
    /**
     *
     */
    private Integer e91;
    /**
     *
     */
    private Integer e92;
    /**
     *
     */
    private Integer e93;
    /**
     *
     */
    private Integer e94;
    /**
     *
     */
    private Integer e95;
    /**
     *
     */
    private Integer e96;
    /**
     *
     */
    private Integer e97;
    /**
     *
     */
    private Integer e98;
    /**
     *
     */
    private Integer e99;

}
