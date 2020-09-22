package by.itechart.library.entity;

import lombok.Getter;

public enum BorrowRecordStatus {
    RETURNED(1),
    RETURNED_AND_DAMAGED(2),
    LOST(3);
    @Getter
    private int borrowRecordStatusId;

    BorrowRecordStatus(int borrowRecordStatusId) {
        this.borrowRecordStatusId = borrowRecordStatusId;
    }
}
