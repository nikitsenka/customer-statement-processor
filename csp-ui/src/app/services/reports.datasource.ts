import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, Observable, of} from "rxjs";
import {Report} from "../model/report";
import {ReportsService} from "./reports.service";
import {catchError, finalize} from "rxjs/operators";


export class ReportsDataSource implements DataSource<Report> {
    private reportsSubject = new BehaviorSubject<Report[]>([]);

    private loadingSubject = new BehaviorSubject<boolean>(false);

    constructor(private reportsService: ReportsService) {

    }

    loadReports() {
        this.loadingSubject.next(true);

        this.reportsService.findAllReports().pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false))
        )
            .subscribe(reports => this.reportsSubject.next(reports));
    }

    connect(collectionViewer: CollectionViewer): Observable<Report[] | ReadonlyArray<Report>> {
        console.log("Connecting data source");
        return this.reportsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.reportsSubject.complete();
        this.loadingSubject.complete();
    }

}
