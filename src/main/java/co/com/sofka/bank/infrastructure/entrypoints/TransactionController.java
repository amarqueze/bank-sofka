package co.com.sofka.bank.infrastructure.entrypoints;

import co.com.sofka.bank.infrastructure.entrypoints.dto.DepositRequest;
import co.com.sofka.bank.infrastructure.entrypoints.dto.ResponseData;
import co.com.sofka.bank.infrastructure.entrypoints.dto.TransferRequest;
import co.com.sofka.bank.usecase.AccountDepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final AccountDepositor accountDepositor;

    @Autowired
    public TransactionController(AccountDepositor accountDepositor) {
        this.accountDepositor = accountDepositor;
    }

    @PostMapping("/transfer")
    public ResponseEntity<ResponseData> authenticate(@RequestBody TransferRequest transferRequest) {
        boolean resultTransfer = accountDepositor.toDepositFromTransfer(transferRequest.toTransferDeposit());
        return resultTransfer
                ? new ResponseEntity<>(new ResponseData("SUCCESS", ""), HttpStatus.OK) : new ResponseEntity<>(new ResponseData("ERROR", "Transaction failed"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseData> authenticate(@RequestBody DepositRequest depositRequest) {
        boolean resultTransfer = accountDepositor.toDepositFromCash(depositRequest.toCashDeposit());
        return resultTransfer
                ? new ResponseEntity<>(new ResponseData("SUCCESS", ""), HttpStatus.OK) : new ResponseEntity<>(new ResponseData("ERROR", "Transaction failed"), HttpStatus.BAD_REQUEST);
    }
}
