package com.peking.courseresourse.service;

import utils.R;

import javax.servlet.http.HttpServletResponse;

public interface ResourceService {
    R me(Integer id);

    R getPie();

    R getComPie();

    void preview(Integer id, Integer type, HttpServletResponse httpServletResponse);

}
