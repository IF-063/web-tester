//package com.softserve.webtester.service;
//
//import com.softserve.webtester.dto.ResponseDTO;
//import com.softserve.webtester.model.Request;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class ExecutorService {
//
//    @Autowired
//    private RequestService requestService;
//
//    @Autowired
//    private RequestExecuteService requestExecuteService;
//
//    public Map<Integer, List<ResponseDTO>> execute(int environmentId, int [] requestIdArray) {
//
//        List<Request> requestList = requestService.loadArray(requestIdArray);
//
//        Map<Integer, List<ResponseDTO>> responseMap = new HashMap<>();
//
//        responseMap.put(0, requestExecuteService.executeRequests(environmentId, requestList, false));
//
//        return responseMap;
//    }
//
//    public Map<Integer, List<ResponseDTO>> execute(int environmentId, int buildVersionId,
//                                                   int [] collectionIdArray) {
//
//        Map<Integer, List<ResponseDTO>> responseMap = new HashMap<>();
//
//        boolean trigger = !(buildVersionId == 0);
//        for (int i = 0; i < collectionIdArray.length; i++) {
//            responseMap.put(collectionIdArray[i], requestExecuteService.executeRequests(environmentId,
//                    requestService.loadFullRequestsByRequestCollectionId(collectionIdArray[i]),trigger));
//        }
//
//        return responseMap;
//    }
//
//}
