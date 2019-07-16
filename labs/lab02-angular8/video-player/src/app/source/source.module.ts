import { NgModule } from '@angular/core';
import { SourceComponent } from './source.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [SharedModule],
  declarations: [SourceComponent],
})
export class SourceModule {}
