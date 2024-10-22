import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface CurrencyOption {
  code: string;
  name: string;
}

@Component({
  selector: 'app-currency-form',
  templateUrl: './currency-form.component.html',
  styleUrls: ['./currency-form.component.css']
})
export class CurrencyFormComponent {
  selectedCurrency = '';
  name = '';
  response: any = null;
  isLoading = false;

  currencies: CurrencyOption[] = [
    { code: 'THB', name: 'Thai Baht' },
    { code: 'USD', name: 'US Dollar' },
    { code: 'AUD', name: 'Australian Dollar' },
    { code: 'HKD', name: 'Hong Kong Dollar' },
    { code: 'CAD', name: 'Canadian Dollar' },
    { code: 'NZD', name: 'New Zealand Dollar' },
    { code: 'SGD', name: 'Singapore Dollar' },
    { code: 'EUR', name: 'Euro' },
    { code: 'HUF', name: 'Hungarian Forint' },
    { code: 'CHF', name: 'Swiss Franc' },
    { code: 'GBP', name: 'British Pound' },
    { code: 'UAH', name: 'Ukrainian Hryvnia' },
    { code: 'JPY', name: 'Japanese Yen' },
    { code: 'CZK', name: 'Czech Koruna' },
    { code: 'DKK', name: 'Danish Krone' },
    { code: 'ISK', name: 'Icelandic Króna' },
    { code: 'NOK', name: 'Norwegian Krone' },
    { code: 'SEK', name: 'Swedish Krona' },
    { code: 'RON', name: 'Romanian Leu' },
    { code: 'BGN', name: 'Bulgarian Lev' },
    { code: 'TRY', name: 'Turkish Lira' },
    { code: 'ILS', name: 'Israeli Shekel' },
    { code: 'CLP', name: 'Chilean Peso' },
    { code: 'PHP', name: 'Philippine Peso' },
    { code: 'MXN', name: 'Mexican Peso' },
    { code: 'ZAR', name: 'South African Rand' },
    { code: 'BRL', name: 'Brazilian Real' },
    { code: 'MYR', name: 'Malaysian Ringgit' },
    { code: 'IDR', name: 'Indonesian Rupiah' },
    { code: 'INR', name: 'Indian Rupee' },
    { code: 'KRW', name: 'South Korean Won' },
    { code: 'CNY', name: 'Chinese Yuan Renminbi' },
    { code: 'XDR', name: 'Special Drawing Rights (SDR, IMF)' }
  ];

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.isLoading = true;
    this.http.post('http://localhost:8080/currencies/get-current-currency-value-command', {
      currency: this.selectedCurrency,
      name: this.name
    }).subscribe({
      next: (response) => {
        this.response = response;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error:', error);
        this.isLoading = false;
      }
    });
  }
}
