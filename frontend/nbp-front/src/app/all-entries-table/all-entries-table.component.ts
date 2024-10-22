import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface CurrencyRequest {
  currency: string;
  name: string;
  date: string;
  value: number;
}

@Component({
  selector: 'app-all-entries-table',
  templateUrl: './all-entries-table.component.html',
  styleUrl: './all-entries-table.component.css'
})
export class AllEntriesTableComponent implements OnInit{
  requests: CurrencyRequest[] = [];
  isLoading = false;
  error: string | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadRequests();
  }

  loadRequests() {
    this.isLoading = true;
    this.error = null;

    this.http.get<CurrencyRequest[]>('http://localhost:8080/currencies/requests')
      .subscribe({
        next: (data) => {
          this.requests = data;
          this.isLoading = false;
        },
        error: (err) => {
          this.error = 'Failed to load currency requests';
          this.isLoading = false;
          console.error('Error:', err);
        }
      });
  }

  formatDate(date: string): string {
    return new Date(date).toLocaleString();
  }
}
