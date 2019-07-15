import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faStop, faEdit, faTrash, faCheck, faReply, faPlay, faPlus, faMinus,
  faVolumeUp, faThumbsUp, faThumbsDown, faPause, faVolumeDown, faVolumeMute,
  faTimes, faSave } from '@fortawesome/free-solid-svg-icons';
import { AppComponent } from './app.component';
import { VideoComponent } from './video/video.component';
import { VideoViewComponent } from './video/video-view/video-view.component';
import { VideoControlComponent } from './video/video-control/video-control.component';
import { VideoListComponent } from './video/video-list/video-list.component';
import { VideoAddComponent } from './video/video-add/video-add.component';

@NgModule({
  declarations: [
    AppComponent,
    VideoComponent,
    VideoViewComponent,
    VideoControlComponent,
    VideoListComponent,
    VideoAddComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    // Add an icon to the library for convenient access in other components
    library.add(faStop, faEdit, faTrash, faCheck, faReply, faPlay, faPause,
      faPlus, faMinus, faVolumeUp, faVolumeDown, faVolumeMute, faThumbsUp, faThumbsDown, faTimes, faSave);
  }
}
