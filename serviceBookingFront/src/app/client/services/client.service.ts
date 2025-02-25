import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/basic/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getAllAds(): Observable<any> {
    return this.http.get(BASIC_URL + `api/client/ads`, {
      headers: this.createAuthorizationHeader()
    })
  }

  searchByName(name: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/client/search/${name}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAdDetailsById(adId: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/client/ad/details/${adId}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  bookService(bookDTO: any): Observable<any> {
    return this.http.post(BASIC_URL + `api/client/book-service`, bookDTO, {
      headers: this.createAuthorizationHeader()
    })
  }

  getMyBookings(): Observable<any> {
    const userId = UserStorageService.getUserId();
    return this.http.get(BASIC_URL + `api/client/my-bookings/${userId}`,{
      headers: this.createAuthorizationHeader()
    })
  }

  giveReview(reviewDTO: any): Observable<any> {
    return this.http.post(BASIC_URL + `api/client/review`, reviewDTO, {
      headers: this.createAuthorizationHeader()
    })
  }


  createAuthorizationHeader(): HttpHeaders{
    const token = UserStorageService.getToken();
    let authHeaders = new HttpHeaders();

    if(token){
      return authHeaders.set('Authorization', 'Bearer ' + token)
    }else{
      console.error('Token Missing !');
      return authHeaders;
    }
  }
}
