import {Component, OnInit} from '@angular/core';
import {ReportsDataSource} from "../services/reports.datasource";
import {ReportsService} from "../services/reports.service";

@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrls: ['./report-list.component.css']
})
export class ReportListComponent implements OnInit {
  displayedColumns = ['id', 'processTime'];
  dataSource: ReportsDataSource;

  constructor(private reportsService: ReportsService) {

  }

  ngOnInit(): void {

    this.dataSource = new ReportsDataSource(this.reportsService);

    this.dataSource.loadReports();
  }
}
