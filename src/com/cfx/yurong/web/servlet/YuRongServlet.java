package com.cfx.yurong.web.servlet;

import com.cfx.yurong.entity.*;
import com.cfx.yurong.service.IFbpService;
import com.cfx.yurong.service.IYuRongService;
import com.cfx.yurong.service.serviceimpl.FbpServiceImpl;
import com.cfx.yurong.service.serviceimpl.YuRongServiceImpl;
import com.cfx.yurong.utils.Logit;
import com.cfx.yurong.utils.StringToNumUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import static com.cfx.yurong.utils.Constants.*;

/**
 * Project: YuRong
 * Create By: Chen.F.X
 * DateTime: 2024/3/17 15:55
 **/
public class YuRongServlet extends BaseServlet {
    private static final String TAG = "YuRongServlet";
    private static final String DISPATCHER_SHOW_INDEX = "/views/index.jsp";
    private static final String DISPATCHER_YR_DETAILS_RECORDS = "/views/yr_details.jsp"; // 羽绒记录详情
    private static final String DISPATCHER_UPDATE_YR_RECORDS = "/views/update_yr.jsp"; // 修改羽绒记录详情
    private static final String DISPATCHER_ADD_YR_RECORDS = "/views/add_yr.jsp"; // 添加羽绒记录页面
    private static final String WORK_NAME = "work_name";
    private IYuRongService mYuRongService = new YuRongServiceImpl();
    private IFbpService mFbpService = new FbpServiceImpl();

    /**
     * 展示首页内容
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "showIndex...");
        List<String> yuRongWorkNames = mYuRongService.getAllWorkName();
        Logit.d(TAG, "yuRongWorkNames: " + yuRongWorkNames);
        List<String> fbpWorkNames = mFbpService.getAllWorkName();
        Logit.d(TAG, "fbpWorkNames: " + fbpWorkNames);

        AllYuRongRecordItem allYRRecords = mYuRongService.getAllYRRecords();
        Logit.d(TAG, "allYRRecords: " + allYRRecords);

        HttpSession session = req.getSession();
        session.setAttribute(WORK_NAMES, yuRongWorkNames);
        session.setAttribute(FBP_WORK_NAMES, fbpWorkNames);
        session.setAttribute(RECORDS, allYRRecords);
        req.getRequestDispatcher(DISPATCHER_SHOW_INDEX).forward(req, resp);
    }

    /**
     * 展示每个员工的所有拆羽绒记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showWorkRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "showWorkRecords...");
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
        // 员工名不为空，进行查询员工的拆羽绒记录
        List<YuRongItem> yuRongItems = mYuRongService.queryYuRongByName(workName);
        if (yuRongItems == null || yuRongItems.size() == 0) {
            Logit.d(TAG, "yu rong records is empty");
            // 员工名为空，返回到首页
            resp.sendRedirect(referer);
            return;
        }
        WorkRecordItem workRecordItem = new WorkRecordItem();
        workRecordItem.setWorkName(workName);
        workRecordItem.setYuRongItems(yuRongItems);
        // 羽绒记录不为空，保存到 session 中，转发到详情页面进行展示
        HttpSession session = req.getSession();
        session.setAttribute(WORK_RECORDS, workRecordItem);
        resp.sendRedirect(req.getContextPath() + DISPATCHER_YR_DETAILS_RECORDS);
    }

    /**
     * 显示要修改员工的拆羽绒记录
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
        YuRongItem yuRongItem = mYuRongService.getYuRongItemById(recordId);
        if (yuRongItem == null) {
            Logit.d(TAG, "yuRongItem is null");
            // 该记录为空，返回到原跳转页
            resp.sendRedirect(referer);
            return;
        }
        req.setAttribute("yuRongItem", yuRongItem);
        req.getRequestDispatcher(DISPATCHER_UPDATE_YR_RECORDS).forward(req, resp);
    }

    /**
     * 修改员工的拆羽绒记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void modifyWorkRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "modifyWorkRecords...");
        String workName = req.getParameter("workName");
        String idStr = req.getParameter("id");
        String dateStr = req.getParameter("date");
        String heightStr = req.getParameter("height");
        String kindStr = req.getParameter("kind");
        String trainNumberStr = req.getParameter("trainNumber");
        Logit.d(TAG,  "workName: " + workName + " idStr: " + idStr + " dateStr: " + dateStr + " heightStr: " + heightStr + " kindStr: " + kindStr + " trainNumberStr: " + trainNumberStr);
        String referer = req.getHeader("Referer");
        YuRongItem yuRongItem = new YuRongItem();
        yuRongItem.setWorkName(workName);
        yuRongItem.setId(StringToNumUtils.parseInteger(idStr));
        Date date = Date.valueOf(dateStr);
        yuRongItem.setWorkTime(date);
        yuRongItem.setHeight(StringToNumUtils.parseBigDecimal(heightStr));
        if ("white".equals(kindStr)) {
            yuRongItem.setKind(Kind.WHITE.getKind());
        } else if ("gray".equals(kindStr)) {
            yuRongItem.setKind(Kind.GRAY.getKind());
        }
        switch (trainNumberStr) {
            case "one":
                yuRongItem.setTrainNumber(Train.ONE.getNumber());
                break;
            case "two":
                yuRongItem.setTrainNumber(Train.TWO.getNumber());
                break;
            case "three":
                yuRongItem.setTrainNumber(Train.THREE.getNumber());
                break;
            case "four":
                yuRongItem.setTrainNumber(Train.FOUR.getNumber());
                break;
            case "five":
                yuRongItem.setTrainNumber(Train.FIVE.getNumber());
                break;
            case "six":
                yuRongItem.setTrainNumber(Train.SIX.getNumber());
                break;
            case "seven":
                yuRongItem.setTrainNumber(Train.SEVEN.getNumber());
                break;
            case "eight":
                yuRongItem.setTrainNumber(Train.EIGHT.getNumber());
                break;
            case "nine":
                yuRongItem.setTrainNumber(Train.NINE.getNumber());
                break;
            case "ten":
                yuRongItem.setTrainNumber(Train.TEN.getNumber());
                break;
            case "eleven":
                yuRongItem.setTrainNumber(Train.ELEVEN.getNumber());
                break;
            case "twelve":
                yuRongItem.setTrainNumber(Train.TWELVE.getNumber());
                break;
            case "thirteen":
                yuRongItem.setTrainNumber(Train.THIRTEEN.getNumber());
                break;
            case "fourteen":
                yuRongItem.setTrainNumber(Train.FOURTEEN.getNumber());
                break;
            case "fifteen":
                yuRongItem.setTrainNumber(Train.FIFTEEN.getNumber());
                break;
            case "sixteen":
                yuRongItem.setTrainNumber(Train.SIXTEEN.getNumber());
                break;
            default:
                yuRongItem.setTrainNumber(trainNumberStr);
                break;

        }
        Logit.d(TAG, "yuRongItem: " + yuRongItem);
        boolean isUpdateSuccess = mYuRongService.updateYuRongItem(yuRongItem);
        if (!isUpdateSuccess) {
            // 如果更新失败，返回原界面
            Logit.d(TAG, "update data error");
            resp.sendRedirect(referer);
            return;
        }
        resp.setContentType("text/html;charset=utf-8");
        // 更新数据成功，重定向到账单详情页
        resp.sendRedirect(req.getContextPath() + "/yuRong?action=showWorkRecords&workName=" + URLEncoder.encode(yuRongItem.getWorkName(), "utf-8"));
    }

    /**
     * 展示添加羽绒账单页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void showAddYuRongItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "showAddYuRongItem...");
        String workName = req.getParameter("workName");
        String referer = req.getHeader("Referer");
        if (workName == null || "".equals(workName)) {
            Logit.d(TAG, "work name is null");
            resp.sendRedirect(referer);
            return;
        }
        req.setAttribute("workName", workName);
        req.getRequestDispatcher(DISPATCHER_ADD_YR_RECORDS).forward(req, resp);
    }

    /**
     * 添加羽绒账单记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addYuRongItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "addYuRongItem...");
        String workName = req.getParameter("workName");
        String dateStr = req.getParameter("date");
        String heightStr = req.getParameter("height");
        String kindStr = req.getParameter("kind");
        String trainNumberStr = req.getParameter("trainNumber");
        String inputTrain = req.getParameter("input_train");
        Logit.d(TAG,  "workName: " + workName + " dateStr: " + dateStr + " heightStr: " + heightStr + " kindStr: " + kindStr + " trainNumberStr: " + trainNumberStr + " inputTrain: " + inputTrain);
        String referer = req.getHeader("Referer");
        YuRongItem yuRongItem = new YuRongItem();
        yuRongItem.setWorkName(workName);
        Date date = Date.valueOf(dateStr);
        yuRongItem.setWorkTime(date);
        yuRongItem.setHeight(StringToNumUtils.parseBigDecimal(heightStr));
        if ("white".equals(kindStr)) {
            yuRongItem.setKind(Kind.WHITE.getKind());
        } else if ("gray".equals(kindStr)) {
            yuRongItem.setKind(Kind.GRAY.getKind());
        }
        switch (trainNumberStr) {
            case "one":
                yuRongItem.setTrainNumber(Train.ONE.getNumber());
                break;
            case "two":
                yuRongItem.setTrainNumber(Train.TWO.getNumber());
                break;
            case "three":
                yuRongItem.setTrainNumber(Train.THREE.getNumber());
                break;
            case "four":
                yuRongItem.setTrainNumber(Train.FOUR.getNumber());
                break;
            case "five":
                yuRongItem.setTrainNumber(Train.FIVE.getNumber());
                break;
            case "six":
                yuRongItem.setTrainNumber(Train.SIX.getNumber());
                break;
            case "seven":
                yuRongItem.setTrainNumber(Train.SEVEN.getNumber());
                break;
            case "eight":
                yuRongItem.setTrainNumber(Train.EIGHT.getNumber());
                break;
            case "nine":
                yuRongItem.setTrainNumber(Train.NINE.getNumber());
                break;
            case "ten":
                yuRongItem.setTrainNumber(Train.TEN.getNumber());
                break;
            case "eleven":
                yuRongItem.setTrainNumber(Train.ELEVEN.getNumber());
                break;
            case "twelve":
                yuRongItem.setTrainNumber(Train.TWELVE.getNumber());
                break;
            case "thirteen":
                yuRongItem.setTrainNumber(Train.THIRTEEN.getNumber());
                break;
            case "fourteen":
                yuRongItem.setTrainNumber(Train.FOURTEEN.getNumber());
                break;
            case "fifteen":
                yuRongItem.setTrainNumber(Train.FIFTEEN.getNumber());
                break;
            case "sixteen":
                yuRongItem.setTrainNumber(Train.SIXTEEN.getNumber());
                break;
            case "default_train":
                if (inputTrain != null && !"".equals(inputTrain)) {
                    yuRongItem.setTrainNumber(inputTrain);
                    break;
                }
            default:
                Logit.d(TAG, "train num error: " + trainNumberStr);
                // 回到主页面
                resp.sendRedirect(req.getContextPath());
                break;

        }

        Logit.d(TAG, "yuRongItem: " + yuRongItem);
        boolean isSuccess = mYuRongService.saveYuRongItem(yuRongItem);
        if (!isSuccess) {
            Logit.d(TAG, "save yu rong error");
            // 回到主页面
            resp.sendRedirect(req.getContextPath());
            return;
        }
        // 添加成功，展示详情页
        showWorkRecords(req, resp);
    }

    /**
     * 删除羽绒账单记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteWorkRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "deleteWorkRecords...");
        String idStr = req.getParameter("recordId");
        String referer = req.getHeader("Referer");
        Integer id = StringToNumUtils.parseInteger(idStr);
        boolean isSuccess = mYuRongService.deleteYuRongItem(id);
        if (!isSuccess) {
            Logit.d(TAG, "delete yu rong error");
            resp.sendRedirect(req.getContextPath());
            return;
        }
        // 删除成功，展示详情页
        showWorkRecords(req, resp);
    }


    /**
     * 添加新员工的羽绒账单记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addNewYRWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logit.d(TAG, "addNewYRWork...");
        String workName = req.getParameter("workName");
        String dateStr = req.getParameter("date");
        String heightStr = req.getParameter("height");
        String kindStr = req.getParameter("kind");
        String trainNumberStr = req.getParameter("trainNumber");
        String inputTrain = req.getParameter("input_train");
        Logit.d(TAG,  "workName: " + workName + " dateStr: " + dateStr + " heightStr: " + heightStr + " kindStr: " + kindStr + " trainNumberStr: " + trainNumberStr + " inputTrain: " + inputTrain);
        YuRongItem yuRongItem = new YuRongItem();
        yuRongItem.setWorkName(workName);
        Date date = Date.valueOf(dateStr);
        yuRongItem.setWorkTime(date);
        yuRongItem.setHeight(StringToNumUtils.parseBigDecimal(heightStr));
        if ("white".equals(kindStr)) {
            yuRongItem.setKind(Kind.WHITE.getKind());
        } else if ("gray".equals(kindStr)) {
            yuRongItem.setKind(Kind.GRAY.getKind());
        }
        switch (trainNumberStr) {
            case "one":
                yuRongItem.setTrainNumber(Train.ONE.getNumber());
                break;
            case "two":
                yuRongItem.setTrainNumber(Train.TWO.getNumber());
                break;
            case "three":
                yuRongItem.setTrainNumber(Train.THREE.getNumber());
                break;
            case "four":
                yuRongItem.setTrainNumber(Train.FOUR.getNumber());
                break;
            case "five":
                yuRongItem.setTrainNumber(Train.FIVE.getNumber());
                break;
            case "six":
                yuRongItem.setTrainNumber(Train.SIX.getNumber());
                break;
            case "seven":
                yuRongItem.setTrainNumber(Train.SEVEN.getNumber());
                break;
            case "eight":
                yuRongItem.setTrainNumber(Train.EIGHT.getNumber());
                break;
            case "nine":
                yuRongItem.setTrainNumber(Train.NINE.getNumber());
                break;
            case "ten":
                yuRongItem.setTrainNumber(Train.TEN.getNumber());
                break;
            case "eleven":
                yuRongItem.setTrainNumber(Train.ELEVEN.getNumber());
                break;
            case "twelve":
                yuRongItem.setTrainNumber(Train.TWELVE.getNumber());
                break;
            case "thirteen":
                yuRongItem.setTrainNumber(Train.THIRTEEN.getNumber());
                break;
            case "fourteen":
                yuRongItem.setTrainNumber(Train.FOURTEEN.getNumber());
                break;
            case "fifteen":
                yuRongItem.setTrainNumber(Train.FIFTEEN.getNumber());
                break;
            case "sixteen":
                yuRongItem.setTrainNumber(Train.SIXTEEN.getNumber());
                break;
            case "default_train":
                if (inputTrain != null && !"".equals(inputTrain)) {
                    yuRongItem.setTrainNumber(inputTrain);
                    break;
                }
            default:
                Logit.d(TAG, "train num error: " + trainNumberStr);
                // 回到主页面
                resp.sendRedirect(req.getContextPath());
                break;

        }
        Logit.d(TAG, "yuRongItem: " + yuRongItem);
        boolean isSuccess = mYuRongService.saveYuRongItem(yuRongItem);
        if (!isSuccess) {
            Logit.d(TAG, "save new yu rong error");
            // 回到主页面
            resp.sendRedirect(req.getContextPath());
            return;
        }
        Logit.d(TAG, "save new yu rong success");
        // 添加成功，展示详情页
        showWorkRecords(req, resp);
    }

}
