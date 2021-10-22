import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from "rxjs";

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.css']
})
export class FileComponent implements OnInit {
  private baseUrl = 'http://localhost:8081';
  selectedFile: File = null;
  data: any | undefined;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
  }

  getAllData() {
    return this.http.get(this.baseUrl + '/upload/get-all').subscribe(
      (data: any) => this.data = data.content);
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload/add`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
// TODO https://www.bezkoder.com/angular-spring-boot-file-upload/ do it
  // onUpload() {
  //   console.log(JSON.stringify(this.selectedFile));
  //   const fd = new FormData();
  //   fd.append('image', this.selectedFile, this.selectedFile.name);
  //   this.http.post('http://localhost:8081/upload/add', fd, {
  //     reportProgress: true,
  //     observe: 'events'
  //   }).toPromise();
  //   // .subscribe(event => {
  //   //   if (event.type === HttpEventType.UploadProgress) {
  //   //     console.log('Upload Progress: ' + Math.round(event.loaded / event.total) + '%');
  //   //   } else if (event.type === HttpEventType.Response) {
  //   //     console.log(event);
  //   //   }
  //   // });
  // }
}
