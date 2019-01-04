package com.tsdproject.pokerplanning.service

import com.tsdproject.pokerplanning.database.LocalDatabase
import com.tsdproject.pokerplanning.model.transportobjects.*
import com.tsdproject.pokerplanning.service.receivers.*
import retrofit2.HttpException
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
import timber.log.Timber

object ServiceManager {

    fun login(userLogin: UserLoginTO, receiver: LoginReceiver) {
        setupRequest(ServiceProvider
            .usersService
            ?.login(userLogin),
            Action1 {
                receiver.onLoginSuccess(it as String)
            },
            Action1 { e ->
                if (e is HttpException) {
                    receiver.onLoginError(e.response().errorBody()?.string())
                }
            },
            Action0 {
                Timber.e("login completed")
            })
    }

    fun register(addUser: AddUserTO, receiver: RegisterReceiver) {
        setupRequest(ServiceProvider
            .usersService
            ?.register(addUser),
            Action1 {
                receiver.onRegisterSuccess()
            },
            Action1 { e ->
                if (e is HttpException) {
                    receiver.onRegisterError(e.response().errorBody()?.string())
                }
            },
            Action0 {
                Timber.e("register completed")
            })
    }

    fun getDynamicAddress(receiver: DynamicAddressReceiver) {
        setupRequest(ServiceProvider
            .dynamicAddressService
            ?.getDynamicAddress(),
            Action1 {
                receiver.onGetDynamicAddressSuccess(it as String)
            },
            Action1 { e ->
                receiver.onGetDynamicAddressError()
            },
            Action0 {
                Timber.e("get dynamic address completed")
            })
    }

    fun createTable(receiver: CreateTableReceiver) {
        setupRequest(ServiceProvider
            .playTablesService
            ?.createTable(TokenTO(LocalDatabase.getUserToken())),
            Action1 {
                receiver.onCreateTableSuccess(it as String)
            },
            Action1 { e ->
                receiver.onCreateTableError()
            },
            Action0 {
                Timber.e("get dynamic address completed")
            })
    }

    fun joinTable(receiver: JoinTableReceiver, userTableToken: UserTableTokenTO) {
        setupRequest(ServiceProvider
            .playTablesService
            ?.joinTable(userTableToken),
            Action1 {
                receiver.onJoinTableSuccess()
            },
            Action1 { e ->
                receiver.onJoinTableError()
            },
            Action0 {
                Timber.e("join table completed")
            })
    }

    fun getParticipants(receiver: GetParticipantsReceiver) {
        setupRequest(ServiceProvider
            .playTablesService
            ?.getParticipants(LocalDatabase.getUserToken()),
            Action1 {
                receiver.onGetParticipantsSuccess(it as ParticipantsTO)
            },
            Action1 { e ->
                receiver.onGetParticipantsError()
            },
            Action0 {
                Timber.e("get participants completed")
            })
    }

    fun setTableReadyStatus(isReady: Boolean, receiver: SetTableReadyStatusReceiver) {
        setupRequest(ServiceProvider
            .usersService
            ?.setTableReadyStatus(ReadyTokenTO(LocalDatabase.getUserToken(), isReady)),
            Action1 {
                receiver.onSetTableReadyStatusSuccess()
            },
            Action1 { e ->
                receiver.onSetTableReadyStatusError()
            },
            Action0 {
                Timber.e("set ready status completed")
            })
    }

    fun startGame(receiver: StartGameReceiver) {
        setupRequest(ServiceProvider
            .gamesService
            ?.startGame(TokenTO(LocalDatabase.getUserToken())),
            Action1 {
                receiver.onStartGameSuccess()
            },
            Action1 { e ->
                receiver.onStartGameError()
            },
            Action0 {
                Timber.e("on start game completed")
            })
    }

    fun isGameStarted(receiver: IsGameStartedReceiver) {
        setupRequest(ServiceProvider
            .gamesService
            ?.isGameStarted(LocalDatabase.getUserToken()),
            Action1 {
                receiver.onIsGameStartedSuccess(it as Boolean?)
            },
            Action1 { e ->
                receiver.onIsGameStartedError()
            },
            Action0 {
                Timber.e("is game started completed")
            })
    }

    fun sendAnswer(receiver: SendAnswerReceiver, answer: String) {
        setupRequest(ServiceProvider
            .gamesService
            ?.sendAnswer(AnswerTokenTO(answer, LocalDatabase.getUserToken())),
            Action1 {
                receiver.onSendAnswerSuccess()
            },
            Action1 { e ->
                receiver.onSendAnswerError()
            },
            Action0 {
                Timber.e("on send answer completed")
            })
    }

    fun kickParticipant(receiver: KickParticipantReceiver, email: String) {
        setupRequest(ServiceProvider
            .playTablesService
            ?.kickParticipants(TokenAndEmailTO(LocalDatabase.getUserToken(), email)),
            Action1 {
                receiver.onKickParticipantSuccess()
            },
            Action1 { e ->
                receiver.onKickParticipantError()
            },
            Action0 {
                Timber.e("on kick participant completed")
            })
    }

    fun setEstimationTaskName(receiver: SetTaskNameReceiver, taskName: String) {
        setupRequest(ServiceProvider
            .playTablesService
            ?.setTaskName(TaskNameTokenTO(LocalDatabase.getUserToken(), taskName)),
            Action1 {
                receiver.onSetTaskNameSuccess()
            },
            Action1 { e ->
                receiver.onSetTaskNameError()
            },
            Action0 {
                Timber.e("on set estimation task name completed")
            })
    }
    fun getResults(receiver: GetResultsReceiver) {
        setupRequest(ServiceProvider
            .gamesService
            ?.getResults(LocalDatabase.getUserToken()),
            Action1 {
                receiver.onGetResultsSuccess(it as List<ResultTO>)
            },
            Action1 { e ->
                receiver.onGetResultsError()
            },
            Action0 {
                Timber.e("on get results completed")
            })
    }

    fun setGameReadyStatus(receiver: SetGameReadyStatusReceiver, isReady: Boolean) {
        setupRequest(ServiceProvider
            .gamesService
            ?.setGameReadyStatus(ReadyTokenTO(LocalDatabase.getUserToken(), isReady)),
            Action1 {
                receiver.onSetGameReadyStatusSuccess()
            },
            Action1 { e ->
                receiver.onSetGameReadyStatusError()
            },
            Action0 {
                Timber.e("set game ready status completed")
            })
    }

    fun isGameFinished(receiver: IsGameFinishedReceiver) {
        setupRequest(ServiceProvider
            .gamesService
            ?.isGameFinished(LocalDatabase.getUserToken()),
            Action1 {
                receiver.onIsGameFinishedSuccess(it as Boolean?)
            },
            Action1 { e ->
                receiver.onIsGameFinishedError()
            },
            Action0 {
                Timber.e("is game finished completed")
            })
    }

    private fun setupRequest(
        observable: Observable<*>?,
        onNext: Action1<Any>,
        onError: Action1<Throwable>,
        onCompleted: Action0
    ): Subscription? {
        return observable
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(onNext, onError, onCompleted)
    }
}