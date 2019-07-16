import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SourceComponent } from './source';
import { VideoPlayerComponent } from './video-player/video-player.component';

const routes: Routes = [
  { path: '', redirectTo: 'sources', pathMatch: 'full' },
  { path: 'play', component: VideoPlayerComponent, pathMatch: 'full' },
  { path: 'sources', component: SourceComponent, pathMatch: 'full' },
  { path: '**', component: SourceComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
