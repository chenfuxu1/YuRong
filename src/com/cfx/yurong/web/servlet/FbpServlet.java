package com.cfx.yurong.web.servlet;

import com.cfx.yurong.entity.*;
import com.cfx.yurong.service.IFbpService;
import com.cfx.yurong.service.serviceimpl.FbpServiceImpl;
import com.cfx.yurong.utils.Logit;
import com.cfx.yurong.utils.StringToNumUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import static com.cfx.yurong.utils.Constants.FBP_WORK_RECORDS;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/23 11:45
 **/
public class FbpServlet extends BaseServlet {
    private static final String TAG = "FbpServlet";
    private static final String DISPATCHER_FBP_DETAILS_RECORDS = "/views/fbp_details.jsp"; // 风包皮记录详情
    private static final String DISPATCHER_UPDATE_FBP_RECORDS = "/views/update_fbp.jsp"; // 修改羽绒记录详情
    private static final String DISPATCHER_ADD_FBP_RECORDS = "/views/add_fbp.jsp"; // 添加风包皮记录页面


    private IFbpService mFbpService = new FbpServiceImpl();

    /**
     * 展示每个员工的所有风包皮记录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showFbpRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "showFbpRecords...");
        String workName = req.getParameter("workName");
        String referer = req.getHeader("Referer");
        Logit.d(TAG, "workName: " + workName);
        Logit.d(TAG, "referer: " + referer);
        if (workName == null || "".equals(workName)) {
            Logit.d(TAG, "work name is null");
            // 员工名为空，返回到首页
            resp.sendRedirect(referer);
            return;
        }
        // 员工名不为空，进行查询员工的风包皮记录
        List<FbpItem> fbpItems = mFbpService.queryFbpItemsByName(workName);
        if (fbpItems == null || fbpItems.size() == 0) {
            Logit.d(TAG, "fbpItems records is empty");
            // 员工名为空，返回到首页
            resp.sendRedirect(referer);
            return;
        }
        FbpRecordItem fbpRecordItem = new FbpRecordItem();
        fbpRecordItem.setWorkName(workName);
        fbpRecordItem.setFbpItems(fbpItems);
        // 风包皮不为空，保存到 session 中，转发到详情页面进行展示
        HttpSession session = req.getSession();
        session.setAttribute(FBP_WORK_RECORDS, fbpRecordItem);
        resp.sendRedirect(req.getContextPath() + DISPATCHER_FBP_DETAILS_RECORDS);
    }

    /**
     * 显示要修改员工的风包皮记录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateWorkRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "updateWorkRecords...");
        String recordIdStr = req.getParameter("recordId");
        String referer = req.getHeader("Referer");
        int recordId = StringToNumUtils.parseInt(recordIdStr, 0);
        Logit.d(TAG, "recordId: " + recordId);
        FbpItem fbpItem = mFbpService.getFbpItemById(recordId);
        if (fbpItem == null) {
            Logit.d(TAG, "fbpItem is null");
            // 该记录为空，返回到原跳转页
            resp.sendRedirect(referer);
            return;
        }
        req.setAttribute("fbpItem", fbpItem);
        req.getRequestDispatcher(DISPATCHER_UPDATE_FBP_RECORDS).forward(req, resp);
    }

    /**
     * 修改员工的风包皮记录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void modifyFbpWorkRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "modifyFbpWorkRecords...");
        String workName = req.getParameter("workName");
        String idStr = req.getParameter("id");
        String workTimeStr = req.getParameter("workTime");
        String dateKindStr = req.getParameter("dateKind");
        String workHourStr = req.getParameter("workHour");
        Logit.d(TAG, "workName: " + workName + " idStr: " + idStr + " workTimeStr: " + workTimeStr + " dateKindStr: " + dateKindStr + " workHourStr: " + workHourStr);
        String referer = req.getHeader("Referer");
        FbpItem fbpItem = new FbpItem();
        fbpItem.setWorkName(workName);
        fbpItem.setId(StringToNumUtils.parseInteger(idStr));
        Date date = Date.valueOf(workTimeStr);
        fbpItem.setWorkTime(date);
        Integer dateKind = StringToNumUtils.parseInteger(dateKindStr);
        if (DateKind.ALL.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.ALL.getKind());
        } else if (DateKind.AM.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.AM.getKind());
        } else if (DateKind.PM.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.PM.getKind());
        }
        switch (workHourStr) {
            case "1.0":
                fbpItem.setWorkHour(new BigDecimal(1));
                break;
            case "0.5":
                fbpItem.setWorkHour(new BigDecimal("0.5"));
                break;
            case "0.6":
                fbpItem.setWorkHour(new BigDecimal("0.6"));
                break;
            case "0.8":
                fbpItem.setWorkHour(new BigDecimal("0.8"));
                break;
            default:
                break;

        }
        Logit.d(TAG, "fbpItem: " + fbpItem);
        boolean isUpdateSuccess = mFbpService.updateFbpItem(fbpItem);
        if (!isUpdateSuccess) {
            // 如果更新失败，返回原界面
            Logit.d(TAG, "update data error");
            resp.sendRedirect(referer);
            return;
        }
        resp.setContentType("text/html;charset=utf-8");
        // 更新数据成功，重定向到账单详情页
        resp.sendRedirect(req.getContextPath() + "/fbp?action=showFbpRecords&workName=" + URLEncoder.encode(fbpItem.getWorkName(), "utf-8"));
    }

    /**
     * 展示添加风包皮账单页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showAddFbpItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "showAddFbpItem...");
        String workName = req.getParameter("workName");
        String referer = req.getHeader("Referer");
        if (workName == null || "".equals(workName)) {
            Logit.d(TAG, "work name is null");
            resp.sendRedirect(referer);
            return;
        }
        req.setAttribute("workName", workName);
        req.getRequestDispatcher(DISPATCHER_ADD_FBP_RECORDS).forward(req, resp);
    }

    /**
     * 添加风包皮账单记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addFbpItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "addFbpItem...");
        String workName = req.getParameter("workName");
        String workTimeStr = req.getParameter("workTime");
        String dateKindStr = req.getParameter("dateKind");
        String workHourStr = req.getParameter("workHour");
        Logit.d(TAG, "workName: " + workName + " workTimeStr: " + workTimeStr + " dateKindStr: " + dateKindStr + " workHourStr: " + workHourStr);
        FbpItem fbpItem = new FbpItem();
        fbpItem.setWorkName(workName);
        Date date = Date.valueOf(workTimeStr);
        fbpItem.setWorkTime(date);
        Integer dateKind = StringToNumUtils.parseInteger(dateKindStr);
        if (DateKind.ALL.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.ALL.getKind());
        } else if (DateKind.AM.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.AM.getKind());
        } else if (DateKind.PM.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.PM.getKind());
        }
        switch (workHourStr) {
            case "1.0":
                fbpItem.setWorkHour(new BigDecimal(1));
                break;
            case "0.5":
                fbpItem.setWorkHour(new BigDecimal("0.5"));
                break;
            case "0.6":
                fbpItem.setWorkHour(new BigDecimal("0.6"));
                break;
            case "0.8":
                fbpItem.setWorkHour(new BigDecimal("0.8"));
                break;
            default:
                break;

        }
        Logit.d(TAG, "fbpItem: " + fbpItem);
        boolean isSuccess = mFbpService.saveFbpItemItem(fbpItem);
        if (!isSuccess) {
            Logit.d(TAG, "save fbp item error");
            // 回到主页面
            resp.sendRedirect(req.getContextPath());
            return;
        }
        // 添加成功，展示详情页
        showFbpRecords(req, resp);
    }

    /**
     * 删除风包皮账单记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteFbpWorkRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "deleteFbpWorkRecords...");
        String idStr = req.getParameter("recordId");
        Integer id = StringToNumUtils.parseInteger(idStr);
        boolean isSuccess = mFbpService.deleteFbpItem(id);
        if (!isSuccess) {
            Logit.d(TAG, "delete fbp item error");
            resp.sendRedirect(req.getContextPath());
            return;
        }
        // 删除成功，展示详情页
        showFbpRecords(req, resp);
    }

    /**
     * 添加新员工的风包皮账单记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addNewFbpWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "addNewFbpWork...");
        String workName = req.getParameter("workName");
        String workTimeStr = req.getParameter("workTime");
        String dateKindStr = req.getParameter("dateKind");
        String workHourStr = req.getParameter("workHour");
        Logit.d(TAG, "workName: " + workName + " workTimeStr: " + workTimeStr + " dateKindStr: " + dateKindStr + " workHourStr: " + workHourStr);
        FbpItem fbpItem = new FbpItem();
        fbpItem.setWorkName(workName);
        Date date = Date.valueOf(workTimeStr);
        fbpItem.setWorkTime(date);
        Integer dateKind = StringToNumUtils.parseInteger(dateKindStr);
        if (DateKind.ALL.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.ALL.getKind());
        } else if (DateKind.AM.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.AM.getKind());
        } else if (DateKind.PM.getKind().equals(dateKind)) {
            fbpItem.setDateKind(DateKind.PM.getKind());
        }
        switch (workHourStr) {
            case "1.0":
                fbpItem.setWorkHour(new BigDecimal(1));
                break;
            case "0.5":
                fbpItem.setWorkHour(new BigDecimal("0.5"));
                break;
            case "0.6":
                fbpItem.setWorkHour(new BigDecimal("0.6"));
                break;
            case "0.8":
                fbpItem.setWorkHour(new BigDecimal("0.8"));
                break;
            default:
                break;

        }
        Logit.d(TAG, "fbpItem: " + fbpItem);
        boolean isSuccess = mFbpService.saveFbpItemItem(fbpItem);
        if (!isSuccess) {
            Logit.d(TAG, "save new fbpItem error");
            // 回到主页面
            resp.sendRedirect(req.getContextPath());
            return;
        }
        Logit.d(TAG, "save new fbpItem success");
        // 添加成功，展示详情页
        showFbpRecords(req, resp);
    }
}
