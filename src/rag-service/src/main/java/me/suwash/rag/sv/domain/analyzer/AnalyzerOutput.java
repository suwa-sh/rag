package me.suwash.rag.sv.domain.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import me.suwash.rag.classification.AnalyzePhase;
import me.suwash.rag.classification.AnalyzeStatus;
import me.suwash.rag.sv.domain.analyze.AnalyzeDetail;
import me.suwash.util.exception.LayerException;

/**
 * 分析処理の出力データモデル。
 */
public class AnalyzerOutput {

    /** バリデーションエラー */
    Set<ConstraintViolation<AnalyzerInput>> violations;
    /** 入力データモデル */
    private AnalyzerInput input;
    /** 進行中の分析処理詳細 */
    private AnalyzeDetail currentDetail;
    /** 分析結果詳細リスト */
    private List<AnalyzeDetail> detailList = new ArrayList<AnalyzeDetail>();

    public AnalyzerOutput() {
        setCurrentDetail();
    }

    public Set<ConstraintViolation<AnalyzerInput>> getViolations() {
        return violations;
    }
    public void setViolations(Set<ConstraintViolation<AnalyzerInput>> violations) {
        this.violations = violations;
    }

    public AnalyzerInput getInput() {
        return input;
    }
    public void setInput(AnalyzerInput input) {
        this.input = input;
    }

    public void setCurrentDetail() {
        currentDetail = new AnalyzeDetail();
        currentDetail.setStatus(AnalyzeStatus.Processing);
    }
    public AnalyzeDetail getCurrentDetail() {
        return currentDetail;
    }

    public void addSuccess(AnalyzePhase phase) {
        currentDetail.setStatus(AnalyzeStatus.Success);
        currentDetail.setPhase(phase);
        detailList.add(currentDetail);
        currentDetail = null;
    }
    public void addWarn(AnalyzePhase phase, LayerException exception) {
        currentDetail.setStatus(AnalyzeStatus.Warning);
        currentDetail.setPhase(phase);
        currentDetail.setException(exception);
        detailList.add(currentDetail);
        currentDetail = null;
    }
    public void addError(AnalyzePhase phase, LayerException exception) {
        currentDetail.setStatus(AnalyzeStatus.Error);
        currentDetail.setPhase(phase);
        currentDetail.setException(exception);
        detailList.add(currentDetail);
        currentDetail = null;
    }
    public void addCancel(AnalyzePhase phase, LayerException exception) {
        currentDetail.setStatus(AnalyzeStatus.Cancel);
        currentDetail.setPhase(phase);
        currentDetail.setException(exception);
        detailList.add(currentDetail);
        currentDetail = null;
    }

    public AnalyzeStatus getStatus() {
        boolean hasWarn = false;
        boolean hasError = false;

        // 現在進行中の分析詳細を確認
        if (currentDetail != null && AnalyzeStatus.Processing.equals(currentDetail.getStatus())) {
            // 実行中の場合、実行中を返却
            return AnalyzeStatus.Processing;
        }

        // 登録されている分析詳細を全件ループ
        for (AnalyzeDetail detail : detailList) {

            // 分析詳細のステータスを確認
            if (AnalyzeStatus.Cancel.equals(detail.getStatus())) {
                // 中止が1件でも存在する場合、中止を返却
                return AnalyzeStatus.Cancel;

            } else if (AnalyzeStatus.Warning.equals(detail.getStatus())) {
                // 警告終了が含まれる場合、フラグを立てる
                hasWarn = true;

            } else if (AnalyzeStatus.Error.equals(detail.getStatus())) {
                // エラー終了が含まれる場合、フラグを立てる
                hasError = true;
            }
        }

        // フラグを確認
        if (hasError) {
            // エラー終了が含まれる場合、エラー終了を返却
            return AnalyzeStatus.Error;

        } else if (hasWarn) {
            // 警告終了が含まれる場合、警告終了を返却
            return AnalyzeStatus.Warning;

        } else {
            // エラー終了も警告終了も含まない場合、成功を返却
            return AnalyzeStatus.Success;
        }
    }


    public Map<AnalyzeStatus, Integer> getStatusCountMap() {

        int processingCount = 0;
        int cancelCount = 0;
        int warningCount = 0;
        int errorCount = 0;
        int successCount = 0;

        // 現在進行中の分析詳細を確認
        if (currentDetail != null && AnalyzeStatus.Processing.equals(currentDetail.getStatus())) {
            // 実行中の場合、実行中を返却
            processingCount++;
        }

        // 登録されている分析詳細を全件ループ
        for (AnalyzeDetail detail : detailList) {
            // 分析詳細のステータスを確認
            if (AnalyzeStatus.Success.equals(detail.getStatus())) {
                successCount++;
            } else if (AnalyzeStatus.Warning.equals(detail.getStatus())) {
                warningCount++;
            } else if (AnalyzeStatus.Error.equals(detail.getStatus())) {
                errorCount++;
            } else if (AnalyzeStatus.Cancel.equals(detail.getStatus())) {
                cancelCount++;
            }
        }

        // カウント結果をMapに登録
        Map<AnalyzeStatus, Integer> resultMap = new HashMap<AnalyzeStatus, Integer>();
        resultMap.put(AnalyzeStatus.Success, successCount);
        resultMap.put(AnalyzeStatus.Warning, warningCount);
        resultMap.put(AnalyzeStatus.Error, errorCount);
        resultMap.put(AnalyzeStatus.Processing, processingCount);
        resultMap.put(AnalyzeStatus.Cancel, cancelCount);

        // 登録したMapを返却
        return resultMap;
    }

    public int getAnalyzedCount() {
        return detailList.size();
    }

    public List<AnalyzeDetail> getDetailList() {
        return detailList;
    }

    public List<AnalyzeDetail> getWarningList() {
        List<AnalyzeDetail> returnList = new ArrayList<AnalyzeDetail>();
        for (AnalyzeDetail detail : detailList) {
            if (AnalyzeStatus.Warning.equals(detail.getStatus())) {
                returnList.add(detail);
            }
        }
        return returnList;
    }

    public List<AnalyzeDetail> getErrorList() {
        List<AnalyzeDetail> returnList = new ArrayList<AnalyzeDetail>();
        for (AnalyzeDetail detail : detailList) {
            if (AnalyzeStatus.Error.equals(detail.getStatus())) {
                returnList.add(detail);
            }
        }
        return returnList;
    }

}
