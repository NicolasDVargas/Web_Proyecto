import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authToken = localStorage.getItem('user');
        if (authToken) {
            let cloned = req.clone({
                headers: req.headers.set('Authorization', `${authToken}`)
            });
            return next.handle(cloned);
        } else {
            return next.handle(req);
        }
    }
}